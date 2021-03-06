package dynamic_beat_Refactoring;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	/* 변수선언 */
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();		// 각 노트가 떨어지는 길 이미지 객체
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();	// 판정라인 이미지 객체

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();					// 게임정보창 이미지 객체

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// S 노트가 떨어지는 길 이미지 객체
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// D노트가 떨어지는 길 이미지 객체
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// F 노트가 떨어지는 길 이미지 객체
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();		// Space 노트가 떨어지는 길 이미지 객체1
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();		// Space 노트가 떨어지는 길 이미지 객체2 
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// J 노트가 떨어지는 길 이미지 객체
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// K 노트가 떨어지는 길 이미지 객체
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();				// L 노트가 떨어지는 길 이미지 객체
	
	private Image blueFlareImage;	// 노트를 맞출 때 나타나는 효과이미지 객체
	private Image judgeImage;			// 노트를 맞출 때 나타나는 판정이미지 : late, good, great....  객체
	
	// 각 키패트 별 누를때 이미지 객체(별로 중요하지않음)
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();				
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	private String titleName;	// 게임제목 = 음악제목
	private String difficulty;		// 게임 난이도
	private String musicTitle;	// 음악 제목
	private Music gameMusic;	// 해당게임의 음악

	ArrayList<Note> noteList = new ArrayList<Note>();	// 각 노트객체들이 생성되는 순간마다 관리하는 arrayList, arrayList에 노트객체를 담아서 사용 
	/* 변수선언 끝 */
	
	
	/* Game 생성자 */
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;	// 게임 타이틀
		this.difficulty = difficulty;		// 게임 난이도
		this.musicTitle = musicTitle;	// 게임 음악제목
		this.gameMusic = new Music(this.musicTitle, false);
	}
	/* Game 생성자 끝 */
	
	
	/* isGameScreen == true일때 실행 : 각 화면의 구성요소 그려주는 메서드 */
	public void screenDraw(Graphics2D g) {
		// 각 노트가 떨어지는 길 그려주는 부분
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		
		// 각 노트 길 구분선 그려주는 부분
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		g.drawImage(gameInfoImage, 0, 660, null);			// 게임정보창 그려주는 부분
		g.drawImage(judgementLineImage, 0, 580, null);	// 판정라인 그려주는 부분
		
		 
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);		// 현재 arrayList에 있는 노트 객체들을 하나씩 불러온다

			if (note.getY() > 620) {				 
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_miss.png/")).getImage();
			}

			if (!note.isProceeded()) {			// close가 이루어진 해당 노트는 리스트에서 제거
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}

		}
		
		// 게임정보창에 게임제목(음악제목), 난이도 그려주는 부분
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		
		// 각 키패드 그려주는 부분
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);

		// 게임 점수 그려주는 부분
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("0", 565, 702);

		g.drawImage(blueFlareImage, 320, 430, null);	// 노트를 맞출 때 나타나는 효과이미지 그려주는 부분
		g.drawImage(judgeImage, 460, 420, null);		// 노트를 맞출 때 나타나는 판정이미지 그려주는 부분

		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed2.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("keyPressed1.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	
	/* 쓰레드 실행 메서드 */
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	/* 쓰레드 중지부분 */
	public void close() {
		gameMusic.close();	// 해당 음악 종료
		this.interrupt();			// 해당쓰레드를 중지상태로 만드는 것(게임부분)
	}
	
	
	/* 각 노트를 떨어뜨리는 메서드, 난이도를 노트가 떨어지는 속도와 노트가 판정바에 도달하는 시간으로 일단 분류 */
	public void dropNotes(String titleName) {
		Beat[] beats = null;

		if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Easy")) {
			Main.note_speed = 3;
			Main.reach_time = 2;
			
			int startTime = 4460 - Main.reach_time  * 1000;
			int gap = 125;			// 비트의 간격, 1/8

			beats = new Beat[] { 
					new Beat(startTime + gap * 1, "S"), 
					new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "S"), 
					new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 9, "S"), 
					new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 15, "D"),
					new Beat(startTime + gap * 18, "J"), 
					new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"),
					new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), 
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), 
					new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 35, "S"),
					new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "S"), 
					new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "S"), 
					new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), 
					new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), 
					new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), 
					new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 54, "S"), 
					new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 59, "F"),
					new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 59, "J"), 
					new Beat(startTime + gap * 61, "L"),
					new Beat(startTime + gap * 63, "K"), 
					new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 65, "Space"), 
					new Beat(startTime + gap * 65, "J"),
					new Beat(startTime + gap * 70, "S"), 
					new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "S"), 
					new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "K"), 
					new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 83, "Space"),
					new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 89, "S"),
					new Beat(startTime + gap * 91, "D"), 
					new Beat(startTime + gap * 93, "F"),
					new Beat(startTime + gap * 96, "Space"), 
					new Beat(startTime + gap * 98, "L"),
					new Beat(startTime + gap * 100, "Space"), 
					new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 104, "D"), 
					new Beat(startTime + gap * 106, "Space"),
					new Beat(startTime + gap * 109, "Space"), 
					new Beat(startTime + gap * 112, "Space"),
					new Beat(startTime + gap * 118, "Space"), 
					new Beat(startTime + gap * 126, "J"),
					new Beat(startTime + gap * 127, "K"), 
					new Beat(startTime + gap * 130, "J"),
					new Beat(startTime + gap * 133, "K"), 
					new Beat(startTime + gap * 136, "L"),
					new Beat(startTime + gap * 138, "S"), 
					new Beat(startTime + gap * 140, "Space"),
					new Beat(startTime + gap * 142, "S"), 
					new Beat(startTime + gap * 144, "Space"),
					new Beat(startTime + gap * 146, "Space"), 
					new Beat(startTime + gap * 150, "Space"),
					new Beat(startTime + gap * 152, "Space"),
					new Beat(startTime + gap * 157, "J"),
					new Beat(startTime + gap * 161, "K"), 
					new Beat(startTime + gap * 165, "L"),
					new Beat(startTime + gap * 167, "S"), 
					new Beat(startTime + gap * 169, "D"),
					new Beat(startTime + gap * 171, "F"), 
					new Beat(startTime + gap * 174, "S"),
					new Beat(startTime + gap * 176, "D"), 
					new Beat(startTime + gap * 178, "F"),
					new Beat(startTime + gap * 180, "Space"), 
					new Beat(startTime + gap * 181, "L"),
					new Beat(startTime + gap * 184, "Space"), 
					new Beat(startTime + gap * 187, "L"),
					new Beat(startTime + gap * 188, "K"), 
					new Beat(startTime + gap * 189, "J"),
					new Beat(startTime + gap * 192, "S"), 
					new Beat(startTime + gap * 192, "Space"),
					new Beat(startTime + gap * 196, "D"), 
					new Beat(startTime + gap * 196, "Space"),
					new Beat(startTime + gap * 200, "S"), 
					new Beat(startTime + gap * 200, "Space"),
					new Beat(startTime + gap * 207, "J"), 
					new Beat(startTime + gap * 207, "Space"),
					new Beat(startTime + gap * 211, "K"), 
					new Beat(startTime + gap * 211, "Space"),
					new Beat(startTime + gap * 216, "L"), 
					new Beat(startTime + gap * 216, "Space"),
					new Beat(startTime + gap * 218, "Space"), 
					new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 223, "K"), 
					new Beat(startTime + gap * 223, "L"),
					new Beat(startTime + gap * 227, "S"), 
					new Beat(startTime + gap * 227, "Space"),
					new Beat(startTime + gap * 231, "D"), 
					new Beat(startTime + gap * 231, "Space"),
					new Beat(startTime + gap * 235, "S"), 
					new Beat(startTime + gap * 235, "Space"),
					new Beat(startTime + gap * 242, "S"), 
					new Beat(startTime + gap * 242, "Space"),
					new Beat(startTime + gap * 242, "L"), 
					new Beat(startTime + gap * 246, "D"),
					new Beat(startTime + gap * 246, "Space"), 
					new Beat(startTime + gap * 246, "K"),
					new Beat(startTime + gap * 250, "F"), 
					new Beat(startTime + gap * 250, "Space"),
					new Beat(startTime + gap * 250, "J"), 
					new Beat(startTime + gap * 255, "J"),
					new Beat(startTime + gap * 257, "K"), 
					new Beat(startTime + gap * 259, "K"),
					new Beat(startTime + gap * 262, "S"), 
					new Beat(startTime + gap * 262, "Space"),
					new Beat(startTime + gap * 266, "D"), 
					new Beat(startTime + gap * 266, "Space"),
					new Beat(startTime + gap * 270, "S"),
					new Beat(startTime + gap * 270, "Space"),
					new Beat(startTime + gap * 275, "J"), 
					new Beat(startTime + gap * 277, "K"),
					new Beat(startTime + gap * 279, "L"),
					new Beat(startTime + gap * 282, "J"),
					new Beat(startTime + gap * 284, "K"),
					new Beat(startTime + gap * 286, "L"),
					new Beat(startTime + gap * 289, "J"), 
					new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 293, "L"), 
					new Beat(startTime + gap * 295, "J"),
					new Beat(startTime + gap * 297, "F"), 
					new Beat(startTime + gap * 299, "D"),
					new Beat(startTime + gap * 301, "S"), 
					new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "D"), 
					new Beat(startTime + gap * 308, "S"),
					new Beat(startTime + gap * 310, "F"), 
					new Beat(startTime + gap * 312, "D"),
					new Beat(startTime + gap * 314, "S"),
					new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 319, "D"), 
					new Beat(startTime + gap * 321, "S"),
					new Beat(startTime + gap * 323, "F"), 
					new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 327, "S"), 
					new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 340, "S"), 
					new Beat(startTime + gap * 332, "Space"),
					new Beat(startTime + gap * 340, "D"), 
					new Beat(startTime + gap * 336, "Space"),
					new Beat(startTime + gap * 340, "S"), 
					new Beat(startTime + gap * 340, "Space"), 
				};
		} else if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Hard")) {
			Main.note_speed = 7;
			Main.reach_time = 1;
			
			int startTime = 4460 - Main.reach_time  * 1000;
			int gap = 125;			// 비트의 간격

			beats = new Beat[] { 
					new Beat(startTime + gap * 1, "S"), 
					new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "S"), 
					new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 9, "S"), 
					new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 15, "D"),
					new Beat(startTime + gap * 18, "J"), 
					new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"),
					new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), 
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), 
					new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 35, "S"),
					new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "S"), 
					new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "S"), 
					new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), 
					new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), 
					new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), 
					new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 54, "S"), 
					new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 59, "F"),
					new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 59, "J"), 
					new Beat(startTime + gap * 61, "L"),
					new Beat(startTime + gap * 63, "K"), 
					new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 65, "Space"), 
					new Beat(startTime + gap * 65, "J"),
					new Beat(startTime + gap * 70, "S"), 
					new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "S"), 
					new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "K"), 
					new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 83, "Space"),
					new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 89, "S"),
					new Beat(startTime + gap * 91, "D"), 
					new Beat(startTime + gap * 93, "F"),
					new Beat(startTime + gap * 96, "Space"), 
					new Beat(startTime + gap * 98, "L"),
					new Beat(startTime + gap * 100, "Space"), 
					new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 104, "D"), 
					new Beat(startTime + gap * 106, "Space"),
					new Beat(startTime + gap * 109, "Space"), 
					new Beat(startTime + gap * 112, "Space"),
					new Beat(startTime + gap * 118, "Space"), 
					new Beat(startTime + gap * 126, "J"),
					new Beat(startTime + gap * 127, "K"), 
					new Beat(startTime + gap * 130, "J"),
					new Beat(startTime + gap * 133, "K"), 
					new Beat(startTime + gap * 136, "L"),
					new Beat(startTime + gap * 138, "S"), 
					new Beat(startTime + gap * 140, "Space"),
					new Beat(startTime + gap * 142, "S"), 
					new Beat(startTime + gap * 144, "Space"),
					new Beat(startTime + gap * 146, "Space"), 
					new Beat(startTime + gap * 150, "Space"),
					new Beat(startTime + gap * 152, "Space"),
					new Beat(startTime + gap * 157, "J"),
					new Beat(startTime + gap * 161, "K"), 
					new Beat(startTime + gap * 165, "L"),
					new Beat(startTime + gap * 167, "S"), 
					new Beat(startTime + gap * 169, "D"),
					new Beat(startTime + gap * 171, "F"), 
					new Beat(startTime + gap * 174, "S"),
					new Beat(startTime + gap * 176, "D"), 
					new Beat(startTime + gap * 178, "F"),
					new Beat(startTime + gap * 180, "Space"), 
					new Beat(startTime + gap * 181, "L"),
					new Beat(startTime + gap * 184, "Space"), 
					new Beat(startTime + gap * 187, "L"),
					new Beat(startTime + gap * 188, "K"), 
					new Beat(startTime + gap * 189, "J"),
					new Beat(startTime + gap * 192, "S"), 
					new Beat(startTime + gap * 192, "Space"),
					new Beat(startTime + gap * 196, "D"), 
					new Beat(startTime + gap * 196, "Space"),
					new Beat(startTime + gap * 200, "S"), 
					new Beat(startTime + gap * 200, "Space"),
					new Beat(startTime + gap * 207, "J"), 
					new Beat(startTime + gap * 207, "Space"),
					new Beat(startTime + gap * 211, "K"), 
					new Beat(startTime + gap * 211, "Space"),
					new Beat(startTime + gap * 216, "L"), 
					new Beat(startTime + gap * 216, "Space"),
					new Beat(startTime + gap * 218, "Space"), 
					new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 223, "K"), 
					new Beat(startTime + gap * 223, "L"),
					new Beat(startTime + gap * 227, "S"), 
					new Beat(startTime + gap * 227, "Space"),
					new Beat(startTime + gap * 231, "D"), 
					new Beat(startTime + gap * 231, "Space"),
					new Beat(startTime + gap * 235, "S"), 
					new Beat(startTime + gap * 235, "Space"),
					new Beat(startTime + gap * 242, "S"), 
					new Beat(startTime + gap * 242, "Space"),
					new Beat(startTime + gap * 242, "L"), 
					new Beat(startTime + gap * 246, "D"),
					new Beat(startTime + gap * 246, "Space"), 
					new Beat(startTime + gap * 246, "K"),
					new Beat(startTime + gap * 250, "F"), 
					new Beat(startTime + gap * 250, "Space"),
					new Beat(startTime + gap * 250, "J"), 
					new Beat(startTime + gap * 255, "J"),
					new Beat(startTime + gap * 257, "K"), 
					new Beat(startTime + gap * 259, "K"),
					new Beat(startTime + gap * 262, "S"), 
					new Beat(startTime + gap * 262, "Space"),
					new Beat(startTime + gap * 266, "D"), 
					new Beat(startTime + gap * 266, "Space"),
					new Beat(startTime + gap * 270, "S"),
					new Beat(startTime + gap * 270, "Space"),
					new Beat(startTime + gap * 275, "J"), 
					new Beat(startTime + gap * 277, "K"),
					new Beat(startTime + gap * 279, "L"),
					new Beat(startTime + gap * 282, "J"),
					new Beat(startTime + gap * 284, "K"),
					new Beat(startTime + gap * 286, "L"),
					new Beat(startTime + gap * 289, "J"), 
					new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 293, "L"), 
					new Beat(startTime + gap * 295, "J"),
					new Beat(startTime + gap * 297, "F"), 
					new Beat(startTime + gap * 299, "D"),
					new Beat(startTime + gap * 301, "S"), 
					new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "D"), 
					new Beat(startTime + gap * 308, "S"),
					new Beat(startTime + gap * 310, "F"), 
					new Beat(startTime + gap * 312, "D"),
					new Beat(startTime + gap * 314, "S"),
					new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 319, "D"), 
					new Beat(startTime + gap * 321, "S"),
					new Beat(startTime + gap * 323, "F"), 
					new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 327, "S"), 
					new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 340, "S"), 
					new Beat(startTime + gap * 332, "Space"),
					new Beat(startTime + gap * 340, "D"), 
					new Beat(startTime + gap * 336, "Space"),
					new Beat(startTime + gap * 340, "S"), 
					new Beat(startTime + gap * 340, "Space"), 
				};
		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Easy")) {
			Main.note_speed = 3;
			Main.reach_time = 2;
			
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space") };
		}  else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Hard")) {
			Main.note_speed = 7;
			Main.reach_time = 1;
			
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space") };
		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Easy")) {
			Main.note_speed = 7;
			Main.reach_time = 1;
			
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space") };
		}  else if (titleName.equals("Bensound - Energy") && difficulty.equals("Hard")) {
			Main.note_speed = 7;
			Main.reach_time = 1;
			
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space") };
		} 

		int i = 0; 
		gameMusic.start();

		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;										// 노트가 떨어졌는지 판단하는 변수

			if (beats[i].getTime() <= gameMusic.getTime()) {		// 비트의 해당 시간대가 게임음악의 시간대보다 작거나 같다면
				Note note = new Note(beats[i].getNoteName());	// 새로운 노트객체를 생성
				note.start();														// 노트가 떨어지게끔 하고
				noteList.add(note);											// noteList에 노트를 추가
				i++;
				dropped = true;												// 노트가 떨어졌다면 true
			}

			if (!dropped) {														// 노트가 떨어지지 않았다면 0.01초의 텀을 둔다, 노트가 안정적으로 떨어지게끔 하기위함
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}
	
	// 판정하는 메서드
	public void judge(String noteType) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (noteType.equals(note.getNoteType())) {
				judgeEvent(note.getJudgement());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}

		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_miss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_late.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_good.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_great.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_perfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeImage_early.png")).getImage();
		}
	}
}
