/*
 * SFGraphicsHeaders.h
 *
 *  Created on: 06/ott/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFGRAPHICSHEADERS_H_
#define SFGRAPHICSHEADERS_H_

#ifdef _WIN32
#include <windows.h>
#include <GL/glew.h>
#include <GL/glut.h>
#endif

#ifdef _WIN64
#include <windows.h>
#include <GL/glew.h>
#include <GL/glut.h>
#endif

#ifdef __APPLE__
#include <UIKit/UIKit.h>
#include <GLKit/GLKit.h>
#endif

#ifdef ANDROID
#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>
#endif

#endif /* SFGRAPHICSHEADERS_H_ */
