
# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt

# Matplotlib 3d drawings
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

def plot_quads(quads, view_params=None):
    '''Plot quads into a 3D figure.'''
    verts = sum(quads, [])
    
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    
    if view_params:
        ax.view_init(*view_params)
    
    ax.plot([p.x for p in verts], [p.y for p in verts], [p.z for p in verts], alpha=0.0)
    ax.add_collection3d(Poly3DCollection(quads))
    plt.show()

def plot_edges(edges):
    '''Takes the edges and plot them into a matplolib graph.'''