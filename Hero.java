import java.util.*;
public class Hero extends Character{
	String[] acts={
		"こうげき",
		"まほう",
	};
	String[] magics={
		"フレイムボルト",
		"ヒール",
		"エターナルフォースブリザード",
		"ヘイスト",
		"神速魔",
		"リジェネ",
		"戻る",
	};
	int[] magicGages={2,2,9,4,3,2};
	String[] magicsinfo={
		"(詠唱:"+magicGages[0]+",攻撃魔法)",
		"(詠唱:"+magicGages[1]+",回復魔法)",
		"(詠唱:"+magicGages[2]+",攻撃魔法)",
		"(詠唱:"+magicGages[3]+",行動速度↑バフ)",
		"(詠唱:"+magicGages[4]+",詠唱速度↑バフ)",
		"(詠唱:"+magicGages[5]+",継続回復↑バフ)",
		"",
	};
	int magicNum =0;
	int magicGage=0;
	int agiAdd=0;
	int agiAddCount=0;
	int spellAdd=0;
	int spellAddCount=0;
	int hotAdd=0;
	int hotAddCount=0;
	Hero(String name,int hp,int atk,int agi){
		super(name,hp,atk,agi);
	}
	boolean actGage(){
		if(this.magicNum!=0){
			this.count +=1+this.spellAdd;
			System.out.print(this.name+"HP:"+this.hp+"【spell:");
			for(int i=0;i<magicGage;i++){
				System.out.print(i<this.count?"★":"☆");
			}
		System.out.print("(");
			System.out.print(agiAdd==1?"ヘ":"");
			System.out.print(spellAdd==1?"詠":"");
			System.out.print(hotAdd>0?"リ":"");
			System.out.println(")】");
			if(this.count>=magicGage){
				this.count=0;
				return true;
			}else{
				return false;
			}
		}
		this.count +=this.agi+this.agiAdd;
		System.out.print(this.name+"HP:"+this.hp+"【act:");
		for(int i=0;i<GAGE;i++){
			System.out.print(i<this.count?"◆":"◇");
		}
		System.out.print("(");
		System.out.print(agiAdd==1?"ヘ":"");
		System.out.print(spellAdd==1?"詠":"");
		System.out.print(hotAdd>0?"リ":"");
		System.out.println(")】");
		if(this.count>=GAGE){
			this.count=0;
			return true;
		}else{
			return false;
		}
	}

	void act(Character c){
		this.buffCount();
		this.act1(c);
	}
	void act1(Character c){
		if(this.magicNum!=0){
			switch(this.magicNum){
				case 1:
					this.fire(c);
					break;
				case 2:
					this.heal(c);
					break;
				case 3:
					this.efb(c);
					break;
				case 4:
					this.haste(c);
					break;
				case 5:
					this.spellHaste(c);
					break;
				case 6:
					this.regen(c);
					break;
			}
			this.magicNum=0;
			return;
		}

		for(int i=0;i<acts.length;i++){
			System.out.printf("%d:%s%n",i,acts[i]);
		}
		int idx;
		do{
			System.out.printf("0~%d>>",acts.length-1);
			idx = new Scanner(System.in).nextInt();
		}while(idx<0 || idx>acts.length-1);
		switch(idx){
			case 0:
				super.attack(c);
				break;
			case 1:
				this.magic(c);
				break;
		}
	}
	void magic(Character c){
		for(int i=0;i<magics.length;i++){
			System.out.printf("%d:%s  %s%n",i,magics[i],magicsinfo[i]);
		}
		int idx;
		do{
			System.out.printf("0~%d>>",magics.length-1);
			idx = new Scanner(System.in).nextInt();
		}while(idx<0 || idx>=magics.length);
		if(idx==magics.length-1){
			this.act1(c);
			return;
		}
		System.out.printf("%sは%sの詠唱を開始した%n",this.name,this.magics[idx]);
		this.magicNum=idx+1;
		this.magicGage=this.magicGages[idx];
	}

	void fire(Character c){
		System.out.printf("%sの%s！%sに%dのダメージを与えた！%n",this.name,this.magics[0],c.name,this.atk*3);
		c.setHp(c.getHp()-this.atk*3);
	}
	void heal(Character c){
		System.out.printf("%sの%s！%sのＨＰを%d回復した！%n",this.name,this.magics[1],this.name,20);
		this.setHp(this.getHp()+20);
	}
	void efb(Character c){
		System.out.printf("%sの%s！%sに%dのダメージを与えた！%n",this.name,this.magics[2],c.name,this.atk*20);
		c.setHp(c.getHp()-this.atk*20);
	}
	void haste(Character c){
		System.out.printf("%sの%s！%sはヘイスト状態になった！%n",this.name,this.magics[3],this.name);
		this.agiAdd=1;
		this.agiAddCount=10;
	}
	void spellHaste(Character c){
		System.out.printf("%sの%s！%sは詠唱短縮状態になった！%n",this.name,this.magics[4],this.name);
		this.spellAdd=1;
		this.spellAddCount=10;
	}
	void regen(Character c){
		System.out.printf("%sの%s！%sはリジェネ状態になった！%n",this.name,this.magics[5],this.name);
		this.hotAdd=10;
		this.hotAddCount=10;
	}
	void buffCount(){
		if(agiAddCount>0) agiAddCount--;
		if(agiAdd==1 && agiAddCount==0){
			agiAdd=0;
			System.out.printf("%sのヘイスト効果が終了した%n",this.name);
		}
		if(spellAddCount>0) spellAddCount--;
		if(spellAdd==1 && spellAddCount==0){
			spellAdd=0;
			System.out.printf("%sの詠唱短縮効果が終了した%n",this.name);
		}
		if(hotAddCount>0){
			hotAddCount--;
			System.out.printf("%sのＨＰが%d回復する！%n",this.name,this.hotAdd);
			this.setHp(this.getHp()+this.hotAdd);
		}
		if(hotAdd>0 && hotAddCount==0){
			hotAdd=0;
			System.out.printf("%sのリジェネ効果が終了した%n",this.name);
		}
	}
}
