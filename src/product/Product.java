package product;

/*Product��Ϊ���������ݵĻ�����Ϣ
�Լ����ݷ�װ����Ƶ�
*/

public class Product {
    private String id;              //��CommonUtils.uuid()�Զ����ɵ�ΨһID
    private String barcode;         //������
    private String name;            //����
    private String units;           //��λ
    private String purchasePrice;   //����
    private String salePrice;       //�ۼ�
    private String inventory;       //���

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public String getUnits() {
        return units;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
    public String getSalePrice() {
        return salePrice;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
    public String getInventory() {
        return inventory;
    }
}
