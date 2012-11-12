package at.bitcoinaustria.bitnotar;


import java.io.Serializable;

public class NotaryItem implements Serializable{

	    private String name;
	    private String address;

    //mutable
	    private String txHash;

    //mutable!
    private ItemStatus status;

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public NotaryItem(){}
	    
	    public NotaryItem(String name, String address){
	    	this.name=name;
	    	this.address = address;
	    }
	    


		public String getName() {
			return name;
		}

		public String getAddress() {
			return address;
		}



    @Override
    public String toString() {
        return "NotaryItem{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaryItem that = (NotaryItem) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }
}

