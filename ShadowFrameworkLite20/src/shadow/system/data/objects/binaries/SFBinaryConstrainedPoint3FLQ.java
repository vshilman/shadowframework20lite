package shadow.system.data.objects.binaries;

import shadow.math.SFVertex3f;
import shadow.system.data.objects.SFBinaryValue;

public class SFBinaryConstrainedPoint3FLQ extends SFBinaryValue{
	
	@Override
	public SFBinaryValue clone() {
		return new SFBinaryConstrainedPoint3FLQ();
	}
	
	@Override
	public int getBitSize() {
		return 24;
	}

	public void getVertex3f(SFVertex3f write){

		int indexA=getValue()>>16;
		int indexB=(getValue()>>8) & 0xff;
		int indexC=getValue() & 0xff;
	
		write.setX(SFStaticFixedPointValues.getValueslq().getValue(indexA));
		write.setY(SFStaticFixedPointValues.getValueslq().getValue(indexB));
		write.setZ(SFStaticFixedPointValues.getValueslq().getValue(indexC));
	}
	
	public void getVertex3f(SFVertex3f write,SFVertex3f min,SFVertex3f max){

		int indexA=getValue()>>16;
		int indexB=(getValue()>>8) & 0xff;
		int indexC=getValue() & 0xff;

		write.setX(SFStaticFixedPointValues.getValueslq().getValue(indexA));
		write.setY(SFStaticFixedPointValues.getValueslq().getValue(indexB));
		write.setZ(SFStaticFixedPointValues.getValueslq().getValue(indexC));
		
		write.setX( (min.getX())+write.getX()*(max.getX()-min.getX()));
		write.setY( (min.getY())+write.getY()*(max.getY()-min.getY()));
		write.setZ( (min.getZ())+write.getZ()*(max.getZ()-min.getZ()));
	}
	
	public void setVertex3f(SFVertex3f read){
		
		if(read.getX()<0)
			read.setX(0);
		if(read.getY()<0)
			read.setY(0);
		if(read.getZ()<0)
			read.setZ(0);
		if(read.getX()>1)
			read.setX(1);
		if(read.getY()>1)
			read.setY(1);
		if(read.getZ()>1)
			read.setZ(1);
		
		int indexA=SFStaticFixedPointValues.getValueslq().getIndex(read.getX());
		int indexB=SFStaticFixedPointValues.getValueslq().getIndex(read.getY());
		int indexC=SFStaticFixedPointValues.getValueslq().getIndex(read.getZ());
		
		this.value=(indexA<<16)+(indexB<<8)+indexC;
	}
	
	public void setVertex3f(SFVertex3f read,SFVertex3f min,SFVertex3f max){

		SFVertex3f temp=new SFVertex3f(read);
		temp.setX( (-min.getX()+read.getX())/(max.getX()-min.getX()));
		temp.setY( (-min.getY()+read.getY())/(max.getY()-min.getY()));
		temp.setZ( (-min.getZ()+read.getZ())/(max.getZ()-min.getZ()));
		
		setVertex3f(temp);
	}

}
