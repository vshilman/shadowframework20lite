#ifndef HOUSE_H
#define HOUSE_H

class House{

    int roofHeight;
    int baseWidth;
    int baseHeight;

    public:
        House(int roofHeight, int baseWidth, int baseHeight);

        int getRoofHeight();

        void setRoofHeight(int roofHeight);

        int getBaseWidth();

        void setBaseWidth(int baseWidth);

        int getBaseHeight();

        void setBaseHeight(int baseHeight);
};

#endif // HOUSE_H
