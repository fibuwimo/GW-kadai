public class BattleApp{
	public static void main(String[] args){
		Character h1 = new Hero("ゆうしゃ",50,15,3);
		Character e1 = new Enemy("マタンゴ",1000,10,2);
		System.out.println("バトル開始！");
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if(h1.actGage()) ((Hero)h1).act(e1);
			if(!e1.isAlive) break;
			if(e1.actGage()) e1.attack(h1);
			if(!h1.isAlive) break;
		}
		if(!h1.isAlive){
			System.out.println("敗北");
		}
		if(!e1.isAlive){
			System.out.println("勝利！！");
		}
	}
}
