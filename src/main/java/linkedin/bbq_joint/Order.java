package linkedin.bbq_joint;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem drink;
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem food;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItem getDrink() {
        return drink;
    }

    public void setDrink(MenuItem drink) {
        this.drink = drink;
    }

    public MenuItem getFood() {
        return food;
    }

    public void setFood(MenuItem food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return String.format("Order<id: %s, drink: %s, food: %s>", id, food, drink);
    }

}
