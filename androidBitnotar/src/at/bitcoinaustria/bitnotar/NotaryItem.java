package at.bitcoinaustria.bitnotar;


public class NotaryItem {
	 	private Long id;
	    private String name;
	    private String hash;
	
	    public NotaryItem(){}
	    
	    public NotaryItem(String name, String hash){
	    	this.name=name;
	    	this.hash=hash;
	    }
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}




}

