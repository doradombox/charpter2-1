package priv.scor.entity;

public class CategoryEntity {
    
    private Long id;
    
    private String name;
    
    private Long sales;

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

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public CategoryEntity(String name, Long sales) {
        super();
        this.name = name;
        this.sales = sales;
    }
    
    
    
}
