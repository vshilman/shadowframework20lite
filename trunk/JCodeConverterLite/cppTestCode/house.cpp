#include "house.h"


House::House(int roofHeight, int baseWidth, int baseHeight) {
        super();
        this.roofHeight = roofHeight;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
}

int House::getRoofHeight() {
        return roofHeight;
}

void House::setRoofHeight(int roofHeight) {
        this.roofHeight = roofHeight;
}

int House::getBaseWidth() {
        return baseWidth;
}

void House::setBaseWidth(int baseWidth) {
        this.baseWidth = baseWidth;
}

int House::getBaseHeight() {
        return baseHeight;
}

void House::setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
}
