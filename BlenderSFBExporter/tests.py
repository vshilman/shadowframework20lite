#Managing import the blender way. Dirty hack to get access to files in the same dir.
import bpy
import os, sys
from functools import reduce
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

import unittest

from funchelps import *
class TestFunctionHelps(unittest.TestCase):
    def test_vec_product(self):
        self.assertEqual(vec_product((1,2,4),(1,2,1)), (1,4,4))
        self.assertEqual(vec_product((1,2,4),(0,0,0)), (0,0,0))

    def test_float_range(self):
        mylist = list(float_range(0.0, 1.0, 0.1));
        rightlist = [x * 0.1 for x in range(10 + 1)]
        for i in range(len(mylist)):
            self.assertAlmostEqual(mylist[i], rightlist[i])

        mylist = list(float_range(0.0, 20.0, 0.2));
        rightlist = [x * 0.2 for x in range(100 + 1)]
        for i in range(len(mylist)):
            self.assertAlmostEqual(mylist[i], rightlist[i])

    def test_compare_th(self):
        self.assertEqual(compare_float(0.1, 0.1), 0)
        self.assertEqual(compare_float(0.1, 0.0), 1)
        self.assertEqual(compare_float(0.0, 0.0), 0)

    def runTest(self):
        self.test_vec_product()
        self.test_float_range()
        self.test_compare_th()       

unittest.TextTestRunner().run(TestFunctionHelps())


from geometry import Vertex, Mesh, Edge, Curve, Face
from geometry import Bezier2Curve, InterpolatingBezier2Curve
from geometry import sample1D, sample1Dmulti

def print_bezier(curve, mesh):
    points = [curve.__sample__(mesh, t * 0.1) for t in range(0, 5 + 1)]
    print("%s %s" % (curve.__class__, points))

class TestCurves(unittest.TestCase):
    def setUp(self):
        verts_args = [(0.0, 0.0, 0.0), (0.5, 1.0, 0.0), (1.0, 0.0, 0.0), 
                      (1.5, 0.5, 0.0), (2.0, 0.0, 0.0)]
        verts = [Vertex(args) for args in verts_args]
        self.mesh = Mesh(verts, None, None)

    def test_abs(self):
        self.assertEqual(Vertex([1.0, 2.0, 3.0]), abs(Vertex([1.0, 2.0, 3.0])))
        self.assertEqual(Vertex([1.0, 2.0, 3.0]), abs(Vertex([1.0, -2.0, 3.0])))
        self.assertNotEqual(Vertex([1.0, -2.0, 3.0]), abs(Vertex([1.0, -2.0, 3.0])))
        self.assertNotEqual(Vertex((0.05555557459592819,-0.05555557459592819,0.0)), 
                            Vertex((0.05555557459592819,-0.5,0.0)))
        self.assertFalse(Vertex((0.05555557459592819,-0.05555557459592819,0.0)) < 
                            Vertex((0.05555557459592819,-0.5,0.0)))

    def test_to_edge(self):
        self.assertEqual(Bezier2Curve([0,1,2]).to_edge(), Edge([0,2]))
        self.assertEqual(Edge([0,2]).to_edge(), Edge([0,2]))

    def test_is_sorted(self):
        curves = [Bezier2Curve([1,2,3]), Bezier2Curve([3,4,5]), Bezier2Curve([5,8,9])]
        self.assertTrue(are_sorted(curves))
        curves = [Bezier2Curve([3,2,1]), Bezier2Curve([3,4,5]), Bezier2Curve([5,8,9])]
        self.assertFalse(are_sorted(curves))

    def test_bezier2(self):
        curve = Bezier2Curve([0,1,2])
        mesh = self.mesh
        self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        self.assertNotEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])
        curve = Bezier2Curve([2,3,4])
        self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        self.assertNotEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])

    def test_int_bezier2(self):
        mesh = self.mesh
        curve = InterpolatingBezier2Curve([0,1,2])
        self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        self.assertEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])
        curve = InterpolatingBezier2Curve([2,3,4])
        self.assertEqual(curve.__sample__(mesh, 0), mesh.verts[curve[0]])
        self.assertEqual(curve.__sample__(mesh, 1), mesh.verts[curve[2]])
        self.assertEqual(curve.__sample__(mesh, 0.5), mesh.verts[curve[1]])

    def test_samplers(self):
        mesh = self.mesh
        curve1 = InterpolatingBezier2Curve([0,1,2])
        curve2 = InterpolatingBezier2Curve([2,3,4])
        curves = [curve1, curve2]
        verts = sample1Dmulti(curves, mesh,  0.1)
        self.assertTrue(verts_almost_equal(verts[0], mesh.verts[curve1[0]]))
        self.assertTrue(verts_almost_equal(verts[5], mesh.verts[curve1[2]]))
        self.assertTrue(verts_almost_equal(verts[-1], mesh.verts[curve2[2]]))

    def test_wrong_curve(self):
        with self.assertRaises(AssertionError):
            Bezier2Curve([0,1,2,3])        

    def runTest(self):
        self.test_abs()
        self.test_wrong_curve()
        self.test_int_bezier2()
        self.test_bezier2()
        self.test_to_edge()
        self.test_is_sorted()
        self.test_samplers()

unittest.TextTestRunner().run(TestCurves())

from geometry import remove_curve, remove_face, remove_vertex, get_adj_edges, get_adj_faces, get_adj_vertex

class GeometryOperations(unittest.TestCase):

    def setUp(self):
        verts = list(map(lambda x: Vertex(x), [(0.0, 0.0, 0.0), (0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (1.0, 1.0, 1.0)]))
        edges = list(map(lambda x: Curve(x), [(0, 0, 1), (0, 0, 2), (0, 0, 3), (1, 1, 2), (1, 1, 3), (2, 2, 3)]))
        faces = list(map(lambda x: Face(x), [(0, 1, 2), (1, 2, 3), (2, 3, 4), (3, 4, 5)]))
        self.mesh = Mesh(verts, edges, faces)

    def test_vertex_operations(self):
        self.assertEqual(Vertex((1,1,1)), -Vertex((-1,-1,-1)))
        self.assertEqual(Vertex((1,1,1)) + Vertex((1,2,3)), Vertex((2,3,4)))
        self.assertEqual(Vertex((1,1,1)) - Vertex((1,2,3)), Vertex((0,-1,-2)))
        self.assertEqual(Vertex((1,1,1)) * 2, Vertex((2,2,2)))
        self.assertEqual(2 * Vertex((1,1,1)), Vertex((2,2,2)))
        self.assertEqual(Vertex((1,1,1)) + 1, Vertex((2,2,2)))
        self.assertEqual(1 + Vertex((1,1,1)), Vertex((2,2,2)))
        self.assertEqual(round(Vertex([1.11, 1.11, 1.11])), Vertex([1,1,1]))
        self.assertEqual(round(Vertex([1.11, 1.11, 1.11]), 1), Vertex([1.1,1.1,1.1]))
        self.assertNotEqual(Vertex((0.05555557459592819,-0.05555557459592819,0.0)), 
                            Vertex((0.05555557459592819,-0.5,0.0)))


    def test_adj_funcs(self):
        self.assertEqual(set(get_adj_vertex(self.mesh, 0)), set([1,2,3]))
        self.assertEqual(set(get_adj_edges(self.mesh, 1)), set([0,3,4]))

    def test_remove(self):
        remove_face(self.mesh, 1)
        self.assertEqual(self.mesh.faces, [[0, 1, 2], None, [2, 3, 4], [3, 4, 5]])
        self.mesh.update_indexes()
        self.assertEqual(self.mesh.faces, [[0, 1, 2], [2, 3, 4], [3, 4, 5]])

    def runTest(self):
        self.test_vertex_operations()
        self.test_adj_funcs()
        self.test_remove()

unittest.TextTestRunner().run(GeometryOperations())

from simplemeshes import grid
from geometry import Bezier2Patch, sample2D, get_limiting_verts, squared_error_verts, module

class PatchesOperations(unittest.TestCase):

    def setUp(self):
        self.grid = grid
        self.patch = Bezier2Patch(self.grid.faces[40])

    def test_sampling(self):
        sampled_vs = sample2D(self.patch, self.grid, 1.0)
        corners = get_limiting_verts(self.grid, 40)
        sampled_vsi = [self.grid.verts.index(v) for v in sampled_vs]
        self.assertEqual(set(corners), set(sampled_vsi))

    def test_module(self):
        v = Vertex((1.0, 1.0, 1.0))
        self.assertEqual(module(v), 3.0)

    def test_squared_errors(self):
        vs1 = sample2D(self.patch, self.grid, 1.0)
        vs2 = sample2D(self.patch, self.grid, 1.0)
        self.assertEqual(squared_error_verts(vs1, vs2), 0.0)
        vs2[0] = Vertex((0.05555557459592819,-0.05555557459592819,0.5))
        self.assertNotEqual(squared_error_verts(vs1, vs2), 0.0)

    def test(self):
        self.assertEqual(set(get_adj_vertex(self.grid, 44)), set([43, 54, 34, 45]))

    def runTest(self):
        self.test()
        self.test_sampling()
        self.test_module()
        self.test_squared_errors()

unittest.TextTestRunner().run(PatchesOperations())

from mymath import bernstein_poly, bezier_curve_n, bezier_patch_n

class MyMathTests(unittest.TestCase):
    
    def setUp(self):
        pass
    
    def test_bernstein(self):
        verts = [Vertex([0.0, 0.0, 0.0]), Vertex([1.0, 0.0, 0.0]), Vertex([0.0, 1.0, 0.0]), Vertex([1.0, 1.0, 0.0])]
        patch = bezier_patch_n(verts)
        self.assertEqual(set(tuple(v) for v in verts), set(tuple(patch(u,v)) for u in [0,1] for v in [0,1]))

    def runTest(self):
        self.test_bernstein()

unittest.TextTestRunner().run(MyMathTests())

from mymath import compile_bezier_curve, compile_bezier_patch, sample_func, sample_func_2D, bezier_curve_1, bezier_curve_2, fit_bezier_curve, fit_bezier_patch, bezier_patch_2_tuple

class PatchFitting(unittest.TestCase):

    def setUp(self):
        self.verts = list(map(lambda x: Vertex(x), [(0.0, 0.0, 0.0), (0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (1.0, 1.0, 1.0)]))

    def simple_curve_test(self, coeffs, out_curve):
        func = compile_bezier_curve(coeffs)
        points = list(sample_func(func, 0.1))
        print(fit_bezier_curve(points, out_curve))
        print(coeffs)

    def simple_patch_test(self, coeffs, out_patch):
        func = compile_bezier_patch(coeffs)
        points = list(sample_func_2D(func, 0.1))
        print(fit_bezier_patch(points, out_patch))
        print(coeffs)

    def test_bezier_curves(self):
        #TODO At the moment those are manual tests. Just read the output.
        self.simple_curve_test([Vertex((0.0, 0.0, 0.0)), Vertex((1.0, 1.0, 1.0))], bezier_curve_1)
        self.simple_curve_test([Vertex((0.0, 0.0, 0.0)), Vertex((1.0, 1.0, 1.0))], bezier_curve_2)
        self.simple_curve_test([Vertex((0.0, 0.0, 0.0)), Vertex((5.0, 2.0, 3.0)), Vertex((1.0, 1.0, 1.0))], bezier_curve_2)

    def test_bezier_patch(self):
        #TODO At the moment those are manual tests. Just read the output.
        cpoints = [(0.0, 0.0, 0.0), (0.0, 0.5, 0.0), (0.0, 1.0, 0.0), (0.5, 0.0, 0.0), (0.5, 0.5, 0.0), (0.5, 1.0, 0.0), (1.0, 0.0, 0.0), (1.0, 0.5, 0.0), (1.0, 1.0, 0.0)]
        cverts = [Vertex(p) for p in cpoints]
        bzr_patch = compile_bezier_patch(cverts)
        points = sample_func_2D(bzr_patch, 0.1)
        estimated_points = fit_bezier_patch(points, bezier_patch_2_tuple)

        print("-----------------")
        for i, p in enumerate(cpoints):
            print(estimated_points[i])
            print(cpoints[i])
            print("-----------------")

    def runTest(self):
        self.test_bezier_curves()
        self.test_bezier_patch()



unittest.TextTestRunner().run(PatchFitting())