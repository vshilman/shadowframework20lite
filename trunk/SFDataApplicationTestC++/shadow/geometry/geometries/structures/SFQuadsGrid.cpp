/*
 * SFQuadsGrid.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/geometries/structures/SFQuadsGrid.h"

namespace sf {



SFQuadsGrid::SFQuadsGrid() {
	closeU=false;
	closeV=false;
	vSplits=0;
	uSplits=0;
	uSplits_length=0;
	vSplits_length=0;
}

SFQuadsGrid::~SFQuadsGrid() {
	if(uSplits!=0){
		delete[] uSplits;
	}
	if(vSplits!=0){
		delete[] vSplits;
	}
}

bool SFQuadsGrid::isCloseU() {
	return closeU;
}

void SFQuadsGrid::setCloseU(bool closeU) {
	this->closeU = closeU;
}

bool SFQuadsGrid::isCloseV() {
	return closeV;
}

void SFQuadsGrid::setCloseV(bool closeV) {
	this->closeV = closeV;
}

float* SFQuadsGrid::getvSplits() {
	return vSplits;
}

void SFQuadsGrid::setvSplits(float* vSplits,short vSplits_length) {
	this->vSplits = vSplits;
	this->vSplits_length=vSplits_length;
}

float* SFQuadsGrid::getuSplits() {
	return uSplits;
}

void SFQuadsGrid::setuSplits(float* uSplits,short uSplits_length) {
	this->uSplits = uSplits;
	this->uSplits_length=uSplits_length;
}

float* SFQuadsGrid::getU_splits() {
	return getuSplits();
}

void SFQuadsGrid::setU_splits( float* uSplits,short uSplits_length) {
	setuSplits(uSplits,uSplits_length);
}

float* SFQuadsGrid::getV_splits() {
	return getvSplits();
}

void SFQuadsGrid::setV_splits(float* vSplits,short vSplits_length) {
	setvSplits(vSplits,vSplits_length);
}

int SFQuadsGrid::getNu() {
	return uSplits_length;
}

void SFQuadsGrid::setNu( int nu) {
	delete uSplits;
	setU_splits(new float[nu],nu);
	float stepU=1.0f/(nu-1.0f);
	for (int i = 0; i < nu; i++) {
		getU_splits()[i]=i*stepU;
	}
}

int SFQuadsGrid::getNv() {
	return vSplits_length;
}

void SFQuadsGrid::setNv(int nv) {
	delete vSplits;
	setV_splits(new float[nv],nv);
	float stepV=1.0f/(nv-1.0f);
	for (int i = 0; i < nv; i++) {
		getV_splits()[i]=i*stepV;
	}
}

int SFQuadsGrid::getPartitionedSplitsSize(int n1,int splitsCount){
	return (splitsCount-1)*n1+1;
}

float* SFQuadsGrid::generatePartitionedSplits( int n1, float stepn1, float* vSplits,int vSplits_length) {
	float* vs=new float[(vSplits_length-1)*n1+1];
	for(int i=0;i<(vSplits_length-1)*n1+1;i++){
		int I=i/n1;
		int Ires=i-n1*(I);
		vs[i]=vSplits[I]+(Ires>0?Ires*stepn1*(vSplits[I+1]-vSplits[I]):0);
	}
	return vs;
}

} /* namespace sf */
