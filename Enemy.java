public class Enemy extends Character{
	Enemy(String name,int hp,int atk,int agi){
		super(name,hp,atk,agi);
	}
	boolean actGage(){
		this.count +=this.agi;
		System.out.print("-----------------------------------");
		System.out.print(this.name+"HP:"+this.hp+"【act:");
		for(int i=0;i<GAGE;i++){
			System.out.print(i<this.count?"◆":"◇");
		}
		System.out.println("】");
		if(this.count>=GAGE){
			this.count=0;
			return true;
		}else{
			return false;
		}
	}
}
