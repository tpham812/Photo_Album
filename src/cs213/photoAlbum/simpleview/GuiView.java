package cs213.photoAlbum.simpleview;

public class GuiView extends AbstractView {
	
	protected Login login;
	
	protected Admin admin;
	
	protected Albums album;
	
	protected Search_Photos sp;
	
	public GuiView() {
		super();
		this.login = new Login(this);
		this.admin = new Admin (this);
		this.album = new Albums(this);
		this.sp = new Search_Photos(this);
		
	}
	
	public static void main(String[] args) {
		GuiView guiView = new GuiView();
	}

}
