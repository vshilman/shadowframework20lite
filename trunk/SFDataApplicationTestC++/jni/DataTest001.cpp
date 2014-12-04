#include <jni.h>
#include "DataTest001.h"
#include "shadow\system\data\SFDataAsset.h"
#include "shadow\system\data\SFDataAssetList.h"
#include "shadow\system\data\SFDataObject.h"
#include "shadow\system\data\SFDataAssetObject.h"
#include "shadow\system\data\objects\SFInt.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "shadow\system\data\SFDataCenter.h"

using namespace sf;

JNIEXPORT jintArray JNICALL Java_dataTests_NativeLib1_getData
  (JNIEnv* env, jobject){

	//SFDataAssetObject<SFInt> dataAssetObject(SFDataAsset<SFInt> dataAsset());
	SFDatasetObject<SFInt> datasetObject();

	SFInt intero = datasetObject.getDataset();





}
