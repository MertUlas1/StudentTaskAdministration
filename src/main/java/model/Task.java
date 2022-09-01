package model;

public class Task {
    //Attribute der app.Task
    private int id;
    private String name;
    private String description;
    private String category;
    private Rating rating;
    private int year;



    public boolean validate()  {
        //n
        if (name == null || name.isEmpty()) {
            return false;
        }

        if (description == null || description.isEmpty()) {
            return false;
        }

        if (category == null || category.isEmpty()) {
            return false;
        }


        return rating != null;
    }

    //toString Methode für die Ausgabe
    @Override
    public String toString() {
        return "app.Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }

    //Getter und Setter für die app.Task Klasse
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}


