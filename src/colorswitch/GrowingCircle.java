package colorswitch;
/*Auteurs: Dina Benkirane, Alex Dufour
 */
public final class GrowingCircle extends Circle{
	
	private Level level;
	private int vitesse = 75;

	/**
	 *Constructeur de la classe GrowingCircle
	 * @param x Position Horizontale du cercle
	 * @param y Position verticale du cercle
	 * @param diameter diametre de depart
	 * @param level le level de l'obstacle
	 */
	public GrowingCircle(double x, double y, double diameter, Level level) {
		super(x, y, diameter);
		
		this.renderer = new CircleRenderer(this);
        this.color = (int) (Math.random() * 4);
        this.level = level;
    }

	/**
	 * Permet de changer la couleur de l'obstacle a chaque 2 seconde
	 * @param dt Delta-Temps en secondes
	 */
	@Override
    public void tick(double dt) {
    	timeSinceColorChange += dt;
    	
    	//Change la couleur du cercle
    	if (timeSinceColorChange > 2) {
    		 this.color = (this.color + 1) % 4;
    		 timeSinceColorChange = 0;
    	}
    	
    	/*
    	 * Si le diamètre du cercle arrive au limite maximale et minimale désiré, on change le sens de la vitesse et on met manuellement
    	 * le dimètre du cerlce à cette limite car sinon le diamètre reste coincé (cas de l'élément pris entre deux position).
    	 */
		this.diameter +=  this.vitesse*dt;
			 
    	if(this.diameter > level.getScreenWidth()/1.5)  {
    		this.vitesse = -this.vitesse;
    		this.diameter = level.getScreenWidth()/1.5;
    	}
    	
    	else if((this.diameter < (level.getScreenWidth()/10)))  {
    		this.vitesse = -this.vitesse;
    		this.diameter = level.getScreenWidth()/10;
    	}
    }
}
