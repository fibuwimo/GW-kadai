public abstract class Character{
	String name;
	int hp;
	int atk;
	int agi;
	int count=0;
	final int GAGE=12;
	boolean isAlive;
	Character(String name,int hp,int atk,int agi){
		this.name=name;
		this.hp=hp;
		this.atk=atk;
		this.agi=agi;
		this.isAlive=true;
	}
	abstract boolean actGage();

	void attack(Character c){
		System.out.printf("%sの攻撃！%sに%dのダメージを与えた！%n",this.name,c.name,this.atk);
		c.setHp(c.getHp()-this.atk);
	}

	void setHp(int hp){
		if(hp<=0){
			hp=0;
			this.isAlive=false;
			System.out.printf("%sは倒れた%n",this.name);
		}
		this.hp=hp;
	}
	int getHp(){
		return this.hp;
	}
}
