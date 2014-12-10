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

def runTests(obj):
    '''Run all the methods that start with test in the object'''
    methods = filter(lambda x: x[0:4] == "test", dir(obj))
    for method in methods:
        print("Testing method %s" % method)
        getattr(obj, method)()

import mymath as mmath

import fitting as fit
import funchelps as ut

# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt

# Matplotlib 3d drawings
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

OUT_GRAPH_DIR = "./OUT_GRAPHS/"

def plot_bezier_curve(points, function, figname):
    points_xs = [p[0] for p in points]
    points_ys = [p[1] for p in points]
    
    cpoints = fit.fit_bezier_curve(list(zip(points_xs, points_ys, [0] * len(points_xs))), function)
    cpoints_xs = [p[0] for p in cpoints]
    cpoints_ys = [p[1] for p in cpoints]
    
    curve = geom.BaseCurve([geom.Vertex(p) for p in cpoints], function)
    curve_points = list(geom.sample_curve_samples(curve, 100))
    curve_xs = [p.x for p in curve_points]
    curve_ys = [p.y for p in curve_points]
    
    plt.plot(points_xs, points_ys, "o", color = "blue", label = "Input points")
    plt.plot(cpoints_xs, cpoints_ys, "o", color = "red", label = "Control points")
    plt.plot(curve_xs, curve_ys, color = "green", label = "Bezier curve")
    plt.savefig(OUT_GRAPH_DIR + figname)
    plt.grid()
    plt.legend()
    plt.show()

def plot_beizer_spline_2d(points, function, n, figname, steps=100):
    points_xs = [p[0] for p in points]
    points_ys = [p[1] for p in points]
    
    cpoints_chunks = fit.fit_bezier_spline(points, function, n)
    curves = []
    cpoints = []
    for chunk in cpoints_chunks:
        cpoints += chunk
        verts = map(lambda x: geom.Vertex(x), chunk)
        curves += [geom.BaseCurve(list(verts), function)]
    curve = geom.SuperCurve(curves)
    
    spline_ts = np.linspace(0.0, 1.0, steps)
    spline_points = [curve.t(t) for t in spline_ts]
    curve_xs = [p.x for p in spline_points]
    curve_ys = [p.y for p in spline_points]
    
    # Compute the control points
    cpoints_xs = [cp[0] for cp in cpoints]
    cpoints_ys = [cp[1] for cp in cpoints]
    
    plt.plot(points_xs, points_ys, "o", color = "blue", label = "Input points")
    plt.plot(cpoints_xs, cpoints_ys, "o", color = "red", label = "Control points")
    plt.plot(curve_xs, curve_ys, color = "green", label = "Bezier spline")
    plt.savefig(OUT_GRAPH_DIR + figname)
    plt.grid()
    plt.legend()
    plt.show()

def plot_bezier_spline_3d(points, function, n, figname, steps=100):
    cpoints_chunks = fit.fit_bezier_spline(points, mmath.bezier_curve_3, 5)
    curves = []
    cpoints = []
    for chunk in cpoints_chunks:
        cpoints += chunk
        verts = map(lambda x: geom.Vertex(x), chunk)
        curves += [geom.BaseCurve(list(verts), mmath.bezier_curve_3)]
    curve = geom.SuperCurve(curves)
    
    # Compute the current spline
    spline_ts = np.linspace(0.0, 1.0, steps)
    spline_points = [curve.t(t) for t in spline_ts]
    spline_xs = [p.x for p in spline_points]
    spline_ys = [p.y for p in spline_points]
    spline_zs = [p.z for p in spline_points]
    
    # Compute the current control points
    cpointsx = [cp[0] for cp in cpoints]
    cpointsy = [cp[1] for cp in cpoints]
    cpointsz = [cp[2] for cp in cpoints]
    
    # Draw the graph
    fig = plt.figure()
    ax = fig.gca(projection='3d')
    ax.plot([p[0] for p in points], [p[1] for p in points], [p[2] for p in points], "o", color="blue", label="Input points")
    ax.plot(spline_xs, spline_ys, spline_zs, color="green", label='Bezier spline')
    ax.plot(cpointsx, cpointsy, cpointsz, "o", color="red", label="Control points")
    plt.grid()
    plt.legend()
    plt.show()
    

class SimpleFittingTests(unittest.TestCase):
    def test_halfsin_bezier_fitting_2(self):
        sin = lambda x: math.sin(x)
        sin_xs = np.linspace(0.0, math.pi, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.bezier_curve_2, "halfsin_bezier_fitting_2.pgf")
    
    def test_sin_bezier_fitting_2(self):
        sin = lambda x: math.sin(x)
        sin_xs = np.linspace(0.0, math.pi * 2, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.bezier_curve_2, "sin_bezier_fitting_2.pgf")
        
    def test_sin_bezier_fitting_3(self):
        sin = lambda x: math.sin(x)
        sin_xs = np.linspace(0.0, math.pi * 2, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.bezier_curve_3, "sin_bezier_fitting_3.pgf")
    
    def test_sin_bezier_fitting_interp_3_noisy(self):
        sin = lambda x: math.sin(x)
        sin_xs = np.linspace(0.0, math.pi * 2, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.interp_bezier_curve_3, "noisysin_interpbezier_fitting_3.pgf")
        
    def test_sin_bezier_fitting_interp_3_noisy(self):
        sin = lambda x: math.sin(x)
        sin_xs = np.linspace(0.0, math.pi * 2, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.interp_bezier_curve_3, "noisysin_interpbezier_fitting_3.pgf")
    
    def test_longsin_bezier_fitting_interp_3_noisy(self):
        sin = lambda x: math.sin(x) + 0.5 * math.sin(0.3 * math.pi + x)
        sin_xs = np.linspace(0.0, math.pi * 4, 20)
        sin_ys = np.array([sin(x) for x in sin_xs])
        sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_bezier_curve(points, mmath.interp_bezier_curve_3, "longsin_interpbezier_fitting_3.pgf")
    
    def test_longsin_spline_fitting_interp_3_2_noisy(self):
        sin = lambda x: math.sin(x) + 0.5 * math.sin(0.3 * math.pi + x)
        sin_xs = np.linspace(0.0, math.pi * 4, 50)
        sin_ys = np.array([sin(x) for x in sin_xs])
        sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_beizer_spline_2d(points, mmath.interp_bezier_curve_3, 2, "longsin_spline_3_interpbezier_fitting_2.pgf")
    
    def test_longsin_spline_fitting_interp_3_5(self):
        sin = lambda x: math.sin(x) + 0.5 * math.sin(0.3 * math.pi + x) + 0.5 * math.sin(4 * x)
        sin_xs = np.linspace(0.0, math.pi * 4, 100)
        sin_ys = np.array([sin(x) for x in sin_xs])
        #sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_beizer_spline_2d(points, mmath.interp_bezier_curve_3, 5, "longsin_spline_3_interpbezier_fitting_2.pgf")
    
    def test_longsin_spline_fitting_interp_3_10(self):
        sin = lambda x: math.sin(x) + 0.5 * math.sin(0.3 * math.pi + x) + 0.5 * math.sin(4 * x)
        sin_xs = np.linspace(0.0, math.pi * 4, 100)
        sin_ys = np.array([sin(x) for x in sin_xs])
        #sin_ys = sin_ys + 0.15*np.random.normal(size=len(sin_ys))
        points = list(zip(sin_xs, sin_ys, [0] * len(sin_xs)))
        plot_beizer_spline_2d(points, mmath.interp_bezier_curve_3, 10, "longsin_spline_3_interpbezier_fitting_2.pgf", 1000)
    
    def test_3dcurve_spline_fitting_interp_3_10(self):
        theta = np.linspace(-4 * np.pi, 4 * np.pi, 50)
        zs = np.linspace(-2, 2, 50)
        r = zs**2 + 1
        xs = r * np.sin(theta)
        ys = r * np.cos(theta)
        
        # Fit the data
        points = list(zip(xs, ys, zs))
        
        plot_bezier_spline_3d(points, mmath.interp_bezier_curve_3, 5, "3dcurve_spline_5_interpbezier_fitting_2.pgf", 1000)
    
    #def test_bezier_fitting(self):
        #sin = lambda x: math.sin(x)
        
        #sin_xs = np.linspace(0.0, math.pi, 20)
        #sin_ys = np.array([sin(x) for x in sin_xs])
        
        #cpoints = fit.fit_bezier_curve(list(zip(sin_xs, sin_ys, [0] * len(sin_xs))), mmath.bezier_curve_2)
        #cpoints_xs = [p[0] for p in cpoints]
        #cpoints_ys = [p[1] for p in cpoints]
        
        #curve = geom.BaseCurve([geom.Vertex(p) for p in cpoints], mmath.bezier_curve_2)
        #curve_points = list(geom.sample_curve_samples(curve, 100))
        #curve_xs = [p.x for p in curve_points]
        #curve_ys = [p.y for p in curve_points]
        
        #plt.plot(sin_xs, sin_ys, "o", label = "Input points")
        #plt.plot(cpoints_xs, cpoints_ys, "o", color = "red", label = "Control points")
        #plt.plot(curve_xs, curve_ys, label = "Bezier curve")
        #plt.legend()
        #plt.show()
    
    #def test_very_simple_spline_fitting(self):
        ## Manually construct a sort of sine wave using bezier curves
        #sin = lambda x: math.sin(x)
        
        #xs = np.linspace(0.0, 2 * math.pi, 5)
        #ys = np.array([math.sin(x) for x in xs])
        #cpoints = [geom.Vertex((xs[i], ys[i], 0.0)) for i in range(len(xs))]
        
        ## We need to repeat the control points to have a spline
        #cpoints_chunks = ut.splinify_list(cpoints, 3)
        #curves = [geom.BaseCurve(ps, mmath.interp_bezier_curve_2) for ps in cpoints_chunks]
        #curve = geom.SuperCurve(curves)
        
        ## Plot the current spline
        #spline_ts = np.linspace(0.0, 1.0, 100)
        #spline_points = [curve.t(t) for t in spline_ts]
        #spline_xs = [p.x for p in spline_points]
        #spline_ys = [p.y for p in spline_points]
        
        #plt.plot(spline_xs, spline_ys, label = "Original curve")
        #plt.legend()
        #plt.show()
    
    #def test_spline_fitting(self):
        ## Test a real spline interpolation
        #xs = np.linspace(0.0, 2 * math.pi, 100)
        #ys = np.array([math.sin(x) for x in xs])
        #points = [geom.Vertex((xs[i], ys[i], 0.0)) for i in range(len(xs))]
        
        #cpoints_chunks = fit.fit_bezier_spline(points, mmath.interp_bezier_curve_2, 2)
        #curves = []
        #cpoints = []
        #for chunk in cpoints_chunks:
            #cpoints += chunk
            #verts = map(lambda x: geom.Vertex(x), chunk)
            #curves += [geom.BaseCurve(list(verts), mmath.interp_bezier_curve_2)]
        #curve = geom.SuperCurve(curves)
        
        ## Compute the current spline
        #spline_ts = np.linspace(0.0, 1.0, 100)
        #spline_points = [curve.t(t) for t in spline_ts]
        #spline_xs = [p.x for p in spline_points]
        #spline_ys = [p.y for p in spline_points]
        
        ## Compute the control points
        #cpointsx = [cp[0] for cp in cpoints]
        #cpointsy = [cp[1] for cp in cpoints]
        
        ## Actually plot the graph
        #plt.plot(spline_xs, spline_ys) # Approximate function
        #plt.plot(xs, ys) # Original function
        #plt.plot(cpointsx, cpointsy, "o")
        #plt.show()
    
    #def test_spline_fitting_3d(self):
        #'''Fit a complex 3d curve with a bezier spline of degree three (5 curves).'''
        ## Generate a cool 3d curve
        #theta = np.linspace(-4 * np.pi, 4 * np.pi, 100)
        #zs = np.linspace(-2, 2, 100)
        #r = zs**2 + 1
        #xs = r * np.sin(theta)
        #ys = r * np.cos(theta)
        
        ## Fit the data
        #points = list(zip(xs, ys, zs))
        #cpoints_chunks = fit.fit_bezier_spline(points, mmath.bezier_curve_3, 5)
        #curves = []
        #cpoints = []
        #for chunk in cpoints_chunks:
            #cpoints += chunk
            #verts = map(lambda x: geom.Vertex(x), chunk)
            #curves += [geom.BaseCurve(list(verts), mmath.bezier_curve_3)]
        #curve = geom.SuperCurve(curves)
        
        ## Compute the current spline
        #spline_ts = np.linspace(0.0, 1.0, 100)
        #spline_points = [curve.t(t) for t in spline_ts]
        #spline_xs = [p.x for p in spline_points]
        #spline_ys = [p.y for p in spline_points]
        #spline_zs = [p.z for p in spline_points]
        
        ## Compute the current control points
        #cpointsx = [cp[0] for cp in cpoints]
        #cpointsy = [cp[1] for cp in cpoints]
        #cpointsz = [cp[2] for cp in cpoints]
        
        ## Draw the graph
        #fig = plt.figure()
        #ax = fig.gca(projection='3d')
        #ax.plot(xs, ys, zs, label='parametric curve')
        #ax.plot(spline_xs, spline_ys, spline_zs, label='Fitted spline')
        #ax.plot(cpointsx, cpointsy, cpointsz, "o", label="Control points")
        #plt.legend()
        #plt.show()

runTests(SimpleFittingTests())