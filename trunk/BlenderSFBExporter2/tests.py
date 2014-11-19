import bpy
import os, sys

# Import the current folder in the path.
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

import math
import numpy as np
import unittest
import geometry as geom

#This variable allow tests which are not automatic are printed to stdout.
HUMAN_READABLE_TESTS = False
DRAW_GRAPHS = False
DELIMETER = "=" * 80

def runTests(obj):
    '''Run all the methods that start with test in the object'''
    methods = filter(lambda x: x[0:4] == "test", dir(obj))
    for method in methods:
        print("Testing method %s" % method)
        getattr(obj, method)()


class BaseGeometryTest(unittest.TestCase):
    def test_vert(self):
        vert1 = geom.Vertex((2.0, 3.0, -3.0))
        vert2 = geom.Vertex((2.0, 3.0, 3.0))
        self.assertFalse(np.allclose(vert1, vert2))
        self.assertTrue(np.allclose((vert1 + vert2), geom.Vertex((4.0, 6.0, 0.0))))
        self.assertFalse(np.allclose(2 * vert1, vert1 * 3))
        self.assertTrue(np.allclose(vert2,abs(vert1)))
        self.assertTrue(np.allclose(abs(vert1), abs(vert2)))
        
runTests(BaseGeometryTest())

import mymath as mmath

class MyMathCurvesTests(unittest.TestCase):
    def test_interpolating_curves(self):
        '''We should check that interpolating curves are really interpolating'''
        A = geom.Vertex((0.0, 0.0, 0.0))
        B = geom.Vertex((0.0, 1.0, 0.0))
        C = geom.Vertex((1.0, 1.0, 0.0))
        c = mmath.interp_bezier_curve_2
        
        self.assertTrue(np.allclose(c(0.0, A, B, C), A))
        self.assertTrue(np.allclose(c(0.5, A, B, C), B))
        self.assertTrue(np.allclose(c(1.0, A, B, C), C))
        
        A = geom.Vertex((0.0, 0.0, 0.0))
        B = geom.Vertex((0.5, 2.0, 0.0))
        C = geom.Vertex((1.0, 2.0, 1.0))
        D = geom.Vertex((0.0, 0.0, 1.0))
        c = mmath.interp_bezier_curve_3
        
        self.assertTrue(np.allclose(c(0.0, A, B, C, D), A))
        self.assertTrue(np.allclose(c(1/3, A, B, C, D), B))
        self.assertTrue(np.allclose(c(2/3, A, B, C, D), C))
        self.assertTrue(np.allclose(c(1.0, A, B, C, D), D))

runTests(MyMathCurvesTests())

import fitting as fit
import funchelps as ut

# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt

# Matplotlib 3d drawings
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

class SimpleFittingTests(unittest.TestCase):
    def test_curve2_fitting(self):
        A = geom.Vertex((0.0, 0.0, 0.0))
        B = geom.Vertex((0.5, 2.0, 0.0))
        C = geom.Vertex((1.0, 2.0, 1.0))
        curve = geom.BaseCurve((A, B, C), mmath.interp_bezier_curve_2)
        
        points = list(geom.sample_curve_samples(curve, 100))
        vals = fit.fit_bezier_curve(points, mmath.interp_bezier_curve_2)
        
        self.assertTrue(np.allclose(np.array(vals), np.array([A,B,C])))
        
        if HUMAN_READABLE_TESTS:
            print(DELIMETER)
            print(vals)
            print([A, B, C])
            print(DELIMETER)
    
    def test_curve3_fitting(self):
        A = geom.Vertex((0.0, 0.0, 0.0))
        B = geom.Vertex((0.5, 2.0, 0.0))
        C = geom.Vertex((1.0, 2.0, 1.0))
        D = geom.Vertex((3.0, 3.0, 3.0))
        curve = geom.BaseCurve((A, B, C, D), mmath.bezier_curve_3)
        
        points = list(geom.sample_curve_samples(curve, 50))
        vals = fit.fit_bezier_curve(points, mmath.bezier_curve_3)
        
        self.assertTrue(np.allclose(np.array(vals), np.array([A,B,C,D])))
        
        if HUMAN_READABLE_TESTS:
            print(DELIMETER)
            print(vals)
            print([A, B, C, D])
            print(DELIMETER)
    
    def test_very_simple_spline_fitting(self):
        # Manually construct a sort of sine wave using bezier curves
        sin = lambda x: math.sin(x)
        
        xs = np.linspace(0.0, 2 * math.pi, 5)
        ys = np.array([math.sin(x) for x in xs])
        cpoints = [geom.Vertex((xs[i], ys[i], 0.0)) for i in range(len(xs))]
        
        # We need to repeat the control points to have a spline
        cpoints_chunks = ut.splinify_list(cpoints, 3)
        curves = [geom.BaseCurve(ps, mmath.interp_bezier_curve_2) for ps in cpoints_chunks]
        curve = geom.SuperCurve(curves)
        
        # Plot the current spline
        spline_ts = np.linspace(0.0, 1.0, 100)
        spline_points = [curve.t(t) for t in spline_ts]
        spline_xs = [p.x for p in spline_points]
        spline_ys = [p.y for p in spline_points]
        
        # Print the graph
        if DRAW_GRAPHS:
            plt.plot(spline_xs, spline_ys)
            plt.show()
    
    def test_spline_fitting(self):
        # Test a real spline interpolation
        xs = np.linspace(0.0, 2 * math.pi, 100)
        ys = np.array([math.sin(x) for x in xs])
        points = [geom.Vertex((xs[i], ys[i], 0.0)) for i in range(len(xs))]
        
        cpoints_chunks = fit.fit_bezier_spline(points, mmath.interp_bezier_curve_2, 2)
        curves = []
        cpoints = []
        for chunk in cpoints_chunks:
            cpoints += chunk
            verts = map(lambda x: geom.Vertex(x), chunk)
            curves += [geom.BaseCurve(list(verts), mmath.interp_bezier_curve_2)]
        curve = geom.SuperCurve(curves)
        
        # Compute the current spline
        spline_ts = np.linspace(0.0, 1.0, 100)
        spline_points = [curve.t(t) for t in spline_ts]
        spline_xs = [p.x for p in spline_points]
        spline_ys = [p.y for p in spline_points]
        
        # Compute the control points
        cpointsx = [cp[0] for cp in cpoints]
        cpointsy = [cp[1] for cp in cpoints]
        
        # Actually plot the graph
        if DRAW_GRAPHS:
            plt.plot(spline_xs, spline_ys) # Approximate function
            plt.plot(xs, ys) # Original function
            plt.plot(cpointsx, cpointsy, "o")
            plt.show()
    
    def test_spline_fitting_3d(self):
        '''Fit a complex 3d curve with a bezier spline of degree three (5 curves).'''
        # Generate a cool 3d curve
        theta = np.linspace(-4 * np.pi, 4 * np.pi, 100)
        zs = np.linspace(-2, 2, 100)
        r = zs**2 + 1
        xs = r * np.sin(theta)
        ys = r * np.cos(theta)
        
        # Fit the data
        points = list(zip(xs, ys, zs))
        cpoints_chunks = fit.fit_bezier_spline(points, mmath.bezier_curve_3, 5)
        curves = []
        cpoints = []
        for chunk in cpoints_chunks:
            cpoints += chunk
            verts = map(lambda x: geom.Vertex(x), chunk)
            curves += [geom.BaseCurve(list(verts), mmath.bezier_curve_3)]
        curve = geom.SuperCurve(curves)
        
        # Compute the current spline
        spline_ts = np.linspace(0.0, 1.0, 100)
        spline_points = [curve.t(t) for t in spline_ts]
        spline_xs = [p.x for p in spline_points]
        spline_ys = [p.y for p in spline_points]
        spline_zs = [p.z for p in spline_points]
        
        # Compute the current control points
        cpointsx = [cp[0] for cp in cpoints]
        cpointsy = [cp[1] for cp in cpoints]
        cpointsz = [cp[2] for cp in cpoints]
        
        # Draw the graph
        if DRAW_GRAPHS:
            fig = plt.figure()
            ax = fig.gca(projection='3d')
            ax.plot(xs, ys, zs, label='parametric curve')
            ax.plot(spline_xs, spline_ys, spline_zs, label='Fitted spline')
            ax.plot(cpointsx, cpointsy, cpointsz, "o", label="Control points")
            plt.legend()
            plt.show()
    
    def test_3d_quad_plot_almost_cube(self):
        verts = [geom.Vertex((0.0, 0.0, 0.0)),
                 geom.Vertex((1.0, 0.0, 0.0)),
                 geom.Vertex((1.0, 1.0, 0.0)),
                 geom.Vertex((0.0, 1.0, 0.0))]
        verts2 = [geom.Vertex((0.0, 0.0, 1.0)),
                 geom.Vertex((1.0, 0.0, 1.0)),
                 geom.Vertex((1.0, 1.0, 1.0)),
                 geom.Vertex((0.0, 1.0, 1.0))]
        verts3 = [geom.Vertex((0.0, 0.0, 0.0)),
                 geom.Vertex((0.0, 0.0, 1.0)),
                 geom.Vertex((1.0, 0.0, 1.0)),
                 geom.Vertex((1.0, 0.0, 0.0))]
        verts4 = [geom.Vertex((0.0, 1.0, 0.0)),
                 geom.Vertex((0.0, 1.0, 1.0)),
                 geom.Vertex((1.0, 1.0, 1.0)),
                 geom.Vertex((1.0, 1.0, 0.0))]

        if DRAW_GRAPHS:
            fig = plt.figure()
            ax = fig.add_subplot(111, projection='3d')
            ax.add_collection3d(Poly3DCollection([verts, verts2, verts3, verts4]))
            plt.show()

runTests(SimpleFittingTests())

import utils

class TestUtils(unittest.TestCase):
    def test_get_arguments(self):
        self.assertEquals(utils.get_number_arguments(mmath.bezier_curve_2), 3)
        self.assertEquals(utils.get_number_arguments(mmath.bezier_curve_3), 4)

runTests(TestUtils())

class PolygonsNetTests(unittest.TestCase):
    def test_simple_net(self):
        v0 = geom.Vertex((0.0, 0.0, 0.0))
        v1 = geom.Vertex((1.0, 0.0, 0.0))
        v2 = geom.Vertex((1.0, 1.0, 0.0))
        v3 = geom.Vertex((0.0, 1.0, 0.0))
        
        disp = geom.Vertex((0.0, 0.0, 0.5))
        
        m0 = (v0 + v1) / 2.0 + disp
        m1 = (v1 + v2) / 2.0 + disp
        m2 = (v2 + v3) / 2.0 + disp
        m3 = (v3 + v0) / 2.0 + disp
        
        # Second try
        c1 = geom.SuperCurve([geom.BaseCurve((v0, m0, v1), mmath.interp_bezier_curve_2)])
        c2 = geom.BaseCurve((v1, m1, v2), mmath.interp_bezier_curve_2)
        c3 = geom.BaseCurve((v2, m2, v3), mmath.interp_bezier_curve_2)
        c4 = geom.BaseCurve((v3, m3, v0), mmath.interp_bezier_curve_2)
        
        c1_points = list(geom.sample_curve_samples(c1, 10))
        c2_points = list(geom.sample_curve_samples(c2, 10))
        c3_points = list(geom.sample_curve_samples(c3, 10))
        c4_points = list(geom.sample_curve_samples(c4, 10))
        
        polygon = geom.PolygonsNetQuad((c1, c2, c3, c4))
        points = list(geom.sample_patch_samples(polygon, 70))
        
        if DRAW_GRAPHS:
            fig = plt.figure()
            ax = fig.gca(projection='3d')
            ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points])
            ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points])
            ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points])
            ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points])
            ax.plot([p.x for p in points], [p.y for p in points], [p.z for p in points], "o", label="Geometry points")
            plt.show()
    
    def test_complex_net(self):
        coords1 = [(0.0, 0.0, 0.0), (0.5, 0.0, 1.0), (1.0, -0.2, 0.5), (1.5, 0.0, 0.0), (2.0, -0.1, 0.5)]
        cpoints1 = [geom.Vertex(coord) for coord in coords1]
        curve1 = geom.generate_spline(cpoints1, mmath.interp_bezier_curve_2)
        
        coords2 = [(2.0, -0.1, 0.5), (2.0, 1.0, 1.0), (2.1, 2.0, 0.0)]
        cpoints2 = [geom.Vertex(coord) for coord in coords2]
        curve2 = geom.generate_spline(cpoints2, mmath.interp_bezier_curve_2)
        
        coords3 = [(2.1, 2.0, 0.0), (1.5, 2.0, 0.0), (1.0, 2.1, 0.5), (0.5, 2.0, 1.0), (0.0, 2.0, 0.0)]
        cpoints3 = [geom.Vertex(coord) for coord in coords3]
        curve3 = geom.generate_spline(cpoints3, mmath.interp_bezier_curve_2)
        
        coords4 = [(0.0, 2.0, 0.0), (-0.5, 1.0, 0.0), (0.0, 0.0, 0.0)]
        cpoints4 = [geom.Vertex(coord) for coord in coords4]
        curve4 = geom.generate_spline(cpoints4, mmath.interp_bezier_curve_2)
        
        polygon = geom.PolygonsNetQuad((curve1, curve2, curve3, curve4))
        points = list(geom.sample_patch_samples(polygon, 50))
        
        c1_points = list(geom.sample_curve_samples(curve1, 20))
        c2_points = list(geom.sample_curve_samples(curve2, 20))
        c3_points = list(geom.sample_curve_samples(curve3, 20))
        c4_points = list(geom.sample_curve_samples(curve4, 20))
        
        if True:
            fig = plt.figure()
            ax = fig.gca(projection='3d')
            ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points])
            ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points])
            ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points])
            ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points])
            ax.plot([p.x for p in points], [p.y for p in points], [p.z for p in points], "o", label="Geometry points")
            plt.show()     

runTests(PolygonsNetTests())
print("All tests passed")

#unittest.main()

#class TestFunctionHelps(unittest.TestCase):
    #def test_vec_product(self):
        #self.assertEqual(vec_product((1,2,4),(1,2,1)), (1,4,4))
        #self.assertEqual(vec_product((1,2,4),(0,0,0)), (0,0,0))

    #def test_float_range(self):
        #mylist = list(float_range(0.0, 1.0, 0.1));
        #rightlist = [x * 0.1 for x in range(10 + 1)]
        #for i in range(len(mylist)):
            #self.assertAlmostEqual(mylist[i], rightlist[i])

        #mylist = list(float_range(0.0, 20.0, 0.2));
        #rightlist = [x * 0.2 for x in range(100 + 1)]
        #for i in range(len(mylist)):
            #self.assertAlmostEqual(mylist[i], rightlist[i])

    #def test_almost_equal(self):
        #set1 = [Vertex((0.0, 0.0, 0.0)), Vertex((1.0, 1.0, 1.0))]
        #set2 = [Vertex((0.000000001, 0.000000001, 0.000000000001)), Vertex((1.0000000001, 1.0000001, 1.0000001))]
        #self.assertFalse(set1 == set2)
        #self.assertTrue(almost_equal_lists(set1, set2))

    #def test_compare_th(self):
        #self.assertEqual(compare_float(0.1, 0.1), 0)
        #self.assertEqual(compare_float(0.1, 0.0), 1)
        #self.assertEqual(compare_float(0.0, 0.0), 0)

    #def runTest(self):
        #self.test_vec_product()
        #self.test_float_range()
        #self.test_compare_th()
        #self.test_almost_equal()

#unittest.TextTestRunner().run(TestFunctionHelps())


#from geometry import Vertex, Mesh, Edge, Curve, Face
#from geometry import Bezier2Curve, InterpolatingBezier2Curve
#from geometry import sample1D, sample1Dmulti

#def print_bezier(curve, mesh):
    #points = [curve.__sample__(mesh, t * 0.1) for t in range(0, 5 + 1)]
    #print("%s %s" % (curve.__class__, points))

#class TestCurves(unittest.TestCase):
    #def setUp(self):
        #verts_args = [(0.0, 0.0, 0.0), (0.5, 1.0, 0.0), (1.0, 0.0, 0.0), 
                      #(1.5, 0.5, 0.0), (2.0, 0.0, 0.0)]
        #verts = [Vertex(args) for args in verts_args]
        #self.mesh = Mesh(verts, None, None)

    #def test_abs(self):
        #self.assertEqual(Vertex([1.0, 2.0, 3.0]), abs(Vertex([1.0, 2.0, 3.0])))
        #self.assertEqual(Vertex([1.0, 2.0, 3.0]), abs(Vertex([1.0, -2.0, 3.0])))
        #self.assertNotEqual(Vertex([1.0, -2.0, 3.0]), abs(Vertex([1.0, -2.0, 3.0])))
        #self.assertNotEqual(Vertex((0.05555557459592819,-0.05555557459592819,0.0)), 
                            #Vertex((0.05555557459592819,-0.5,0.0)))
        #self.assertFalse(Vertex((0.05555557459592819,-0.05555557459592819,0.0)) < 
                            #Vertex((0.05555557459592819,-0.5,0.0)))

    #def test_to_edge(self):
        #self.assertEqual(Bezier2Curve([0,1,2]).to_edge(), Edge([0,2]))
        #self.assertEqual(Edge([0,2]).to_edge(), Edge([0,2]))

    #def test_is_sorted(self):
        #curves = [Bezier2Curve([1,2,3]), Bezier2Curve([3,4,5]), Bezier2Curve([5,8,9])]
        #self.assertTrue(are_sorted(curves))
        #curves = [Bezier2Curve([3,2,1]), Bezier2Curve([3,4,5]), Bezier2Curve([5,8,9])]
        #self.assertFalse(are_sorted(curves))

    #def test_bezier2(self):
        #curve = Bezier2Curve([0,1,2])
        #mesh = self.mesh
        #self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        #self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        #self.assertNotEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])
        #curve = Bezier2Curve([2,3,4])
        #self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        #self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        #self.assertNotEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])

    #def test_int_bezier2(self):
        #mesh = self.mesh
        #curve = InterpolatingBezier2Curve([0,1,2])
        #self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        #self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        #self.assertEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])
        #curve = InterpolatingBezier2Curve([2,3,4])
        #self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        #self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        #self.assertEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])

    #def test_samplers(self):
        #mesh = self.mesh
        #curve1 = InterpolatingBezier2Curve([0,1,2])
        #curve2 = InterpolatingBezier2Curve([2,3,4])
        #curves = [curve1, curve2]
        #verts = sample1Dmulti(curves, mesh,  0.1)
        #self.assertTrue(verts_almost_equal(verts[0], mesh.verts[curve1[0]]))
        #self.assertTrue(verts_almost_equal(verts[5], mesh.verts[curve1[2]]))
        #self.assertTrue(verts_almost_equal(verts[-1], mesh.verts[curve2[2]]))

    #def test_wrong_curve(self):
        #with self.assertRaises(AssertionError):
            #Bezier2Curve([0,1,2,3])        

    #def runTest(self):
        #self.test_abs()
        #self.test_wrong_curve()
        #self.test_int_bezier2()
        #self.test_bezier2()
        #self.test_to_edge()
        #self.test_is_sorted()
        #self.test_samplers()

#unittest.TextTestRunner().run(TestCurves())

#from geometry import remove_curve, remove_face, remove_vertex, get_adj_edges, get_adj_faces, get_adj_vertex

#class GeometryOperations(unittest.TestCase):

    #def setUp(self):
        #verts = list(map(lambda x: Vertex(x), [(0.0, 0.0, 0.0), (0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (1.0, 1.0, 1.0)]))
        #edges = list(map(lambda x: Curve(x), [(0, 0, 1), (0, 0, 2), (0, 0, 3), (1, 1, 2), (1, 1, 3), (2, 2, 3)]))
        #faces = list(map(lambda x: Face(x), [(0, 1, 2), (1, 2, 3), (2, 3, 4), (3, 4, 5)]))
        #self.mesh = Mesh(verts, edges, faces)

    #def test_vertex_operations(self):
        #self.assertEqual(Vertex((1,1,1)), -Vertex((-1,-1,-1)))
        #self.assertEqual(Vertex((1,1,1)) + Vertex((1,2,3)), Vertex((2,3,4)))
        #self.assertEqual(Vertex((1,1,1)) - Vertex((1,2,3)), Vertex((0,-1,-2)))
        #self.assertEqual(Vertex((1,1,1)) * 2, Vertex((2,2,2)))
        #self.assertEqual(2 * Vertex((1,1,1)), Vertex((2,2,2)))
        #self.assertEqual(Vertex((1,1,1)) + 1, Vertex((2,2,2)))
        #self.assertEqual(1 + Vertex((1,1,1)), Vertex((2,2,2)))
        #self.assertEqual(round(Vertex([1.11, 1.11, 1.11])), Vertex([1,1,1]))
        #self.assertEqual(round(Vertex([1.11, 1.11, 1.11]), 1), Vertex([1.1,1.1,1.1]))
        #self.assertNotEqual(Vertex((0.05555557459592819,-0.05555557459592819,0.0)), 
                            #Vertex((0.05555557459592819,-0.5,0.0)))


    #def test_adj_funcs(self):
        #self.assertEqual(set(get_adj_vertex(self.mesh, 0)), set([1,2,3]))
        #self.assertEqual(set(get_adj_edges(self.mesh, 1)), set([0,3,4]))

    #def test_remove(self):
        #remove_face(self.mesh, 1)
        #self.assertEqual(self.mesh.faces, [[0, 1, 2], None, [2, 3, 4], [3, 4, 5]])
        #self.mesh.update_indexes()
        #self.assertEqual(self.mesh.faces, [[0, 1, 2], [2, 3, 4], [3, 4, 5]])

    #def runTest(self):
        #self.test_vertex_operations()
        #self.test_adj_funcs()
        #self.test_remove()

#unittest.TextTestRunner().run(GeometryOperations())

#from simplemeshes import grid
#from geometry import Bezier2Patch, sample2D, get_limiting_verts, squared_error_verts, module

#class PatchesOperations(unittest.TestCase):

    #def setUp(self):
        #self.grid = grid
        #self.patch = Bezier2Patch(self.grid.faces[40])

    #def test_sampling(self):
        #sampled_vs = sample2D(self.patch, self.grid, 1.0)
        #corners = get_limiting_verts(self.grid, 40)
        #sampled_vsi = [self.grid.verts.index(v) for v in sampled_vs]
        #self.assertEqual(set(corners), set(sampled_vsi))

    #def test_module(self):
        #v = Vertex((1.0, 1.0, 1.0))
        #self.assertEqual(module(v), 3.0)

    #def test_squared_errors(self):
        #vs1 = sample2D(self.patch, self.grid, 1.0)
        #vs2 = sample2D(self.patch, self.grid, 1.0)
        #self.assertEqual(squared_error_verts(vs1, vs2), 0.0)
        #vs2[0] = Vertex((0.05555557459592819,-0.05555557459592819,0.5))
        #self.assertNotEqual(squared_error_verts(vs1, vs2), 0.0)

    #def test(self):
        #self.assertEqual(set(get_adj_vertex(self.grid, 44)), set([43, 54, 34, 45]))

    #def runTest(self):
        #self.test()
        #self.test_sampling()
        #self.test_module()
        #self.test_squared_errors()

#unittest.TextTestRunner().run(PatchesOperations())

#from mymath import bernstein_poly, bezier_curve_n, bezier_patch_n

#class MyMathTests(unittest.TestCase):
    
    #def setUp(self):
        #pass
    
    #def test_bernstein(self):
        #verts = [Vertex([0.0, 0.0, 0.0]), Vertex([1.0, 0.0, 0.0]), Vertex([0.0, 1.0, 0.0]), Vertex([1.0, 1.0, 0.0])]
        #patch = bezier_patch_n(verts)
        #self.assertEqual(set(tuple(v) for v in verts), set(tuple(patch(u,v)) for u in [0,1] for v in [0,1]))

    #def runTest(self):
        #self.test_bernstein()

#unittest.TextTestRunner().run(MyMathTests())

#from mymath import compile_bezier_curve, compile_bezier_patch, sample_func, sample_func_2D, bezier_curve_1, bezier_curve_2, fit_bezier_curve, fit_bezier_patch, bezier_patch_2_tuple
#from random import uniform
#from simplemeshes import two_patches_points
#class PatchFitting(unittest.TestCase):

    #def setUp(self):
        #self.verts = list(map(lambda x: Vertex(x), [(0.0, 0.0, 0.0), (0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (1.0, 1.0, 1.0)]))

    #def simple_curve_test(self, coeffs, out_curve):
        #func = compile_bezier_curve(coeffs)
        #points = list(sample_func(func, 0.1))
        #estimated_coeffs = [Vertex(x) for x in fit_bezier_curve(points, out_curve)]
        #self.assertTrue(almost_equal_lists(coeffs, estimated_coeffs))

    #def test_bezier_curves(self):
        #self.simple_curve_test([Vertex((0.0, 0.0, 0.0)), Vertex((1.0, 1.0, 1.0))], bezier_curve_1)
        #self.simple_curve_test([Vertex((0.0, 0.0, 0.0)), Vertex((5.0, 2.0, 3.0)), Vertex((1.0, 1.0, 1.0))], bezier_curve_2)

    #def test_bezier_patch(self, cpoints):
        #cverts = [Vertex(p) for p in cpoints]
        #bzr_patch = compile_bezier_patch(cverts)
        #points = sample_func_2D(bzr_patch, 0.1)
        #estimated_points = fit_bezier_patch(points, bezier_patch_2_tuple)
        #estimaded_cverts = [Vertex(p) for p in estimated_points]

        #if VERBOSE:
            #print(".....................................")
            #print(cverts)
            #print(estimaded_cverts)
            #print(".....................................")

        #self.assertTrue(almost_equal_lists(cverts, estimaded_cverts))

    #def test_bezier_patches(self):
        #cpoints = [(0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (0.0, 1.0, 0.0), (0.5, 0.0, 0.0), (0.5, 0.5, 0.0), (0.5, 1.0, 0.0), (1.0, 0.0, 0.0), (1.0, 0.5, 0.0), (1.0, 1.0, 0.0)]
        #cpoints2 = [(uniform(0.0, 1.0), uniform(0.0,1.0), uniform(0.0,1.0))] * 9
        #self.test_bezier_patch(cpoints)
        #self.test_bezier_patch(cpoints2)

    #def test_double_patch(self):
        #points = [Vertex(p) for p in two_patches_points]
        #estimated_points = fit_bezier_patch(points, bezier_patch_2_tuple)
        #print(estimated_points)

    #def runTest(self):
        #self.test_bezier_curves()
        #self.test_bezier_patches()
        #self.test_double_patch()



#unittest.TextTestRunner().run(PatchFitting())
