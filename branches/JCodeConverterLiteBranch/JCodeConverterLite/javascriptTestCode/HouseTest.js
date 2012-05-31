
function main(){
	var house=new House(10,30,40);
	alert(house.getRoofHeight()+" "+house.getBaseWidth()+" "+house.getBaseHeight());
	house.setRoofHeight(2000);
	alert(house.getRoofHeight()+" "+house.getBaseWidth()+" "+house.getBaseHeight());
	house.setBaseWidth(2000);
	alert(house.getRoofHeight()+" "+house.getBaseWidth()+" "+house.getBaseHeight());
	house.setBaseHeight(2000);
	alert(house.getRoofHeight()+" "+house.getBaseWidth()+" "+house.getBaseHeight());
}