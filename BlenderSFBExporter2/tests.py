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
        
        if DRAW_GRAPHS:
            fig = plt.figure()
            ax = fig.gca(projection='3d')
            ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points])
            ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points])
            ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points])
            ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points])
            ax.plot([p.x for p in points], [p.y for p in points], [p.z for p in points], "o", label="Geometry points")
            plt.show()     

runTests(PolygonsNetTests())

import algorithm1

class TestAlgorithm(unittest.TestCase):
    def test_check_split(self):
        edge1 = (1,2,3,4,5,6)
        edge2 = (10, 11, 4, 12, 13, 14, 15)
        edge3 = (6,7,8,9,10)
        self.assertEqual(algorithm1.check_and_split(edge1, edge2), [(1,2,3,4),(4,5,6),(10,11,4),(4,12,13,14,15)])
        self.assertEqual(algorithm1.check_and_split(edge1, edge3), [edge1, edge3])

runTests(TestAlgorithm())


import errors

class TestErrors(unittest.TestCase):
    def test_complex_error(self):
        verts1 = [geom.Vertex((0.0, 0.0, 1.0)),
                  geom.Vertex((0.0, 0.0, 0.0))]
        refs = [geom.Vertex((0.0, 1.0, 1.0)),
                geom.Vertex((0.0, 1.0, 0.0))]
        self.assertEqual(errors.verts_sets_sq_error(verts1, refs), 2)

runTests(TestErrors())
print("All tests passed")
