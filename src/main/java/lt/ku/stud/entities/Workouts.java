package lt.ku.stud.entities;

import jakarta.persistence.*;
import java.util.List;
    @Entity
    @Table(name="Workouts")

    public class Workouts {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column
        private String name;
        @Column
        private String date;
        @Column
        private String places;
        @Column
        private String location;
        @OneToMany(mappedBy = "workouts")
        private List<Registrations> Registrations;

        public Workouts(String name, String date, String places, String location) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.places = places;
            this.location = location;
        }

        public Workouts() {

        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPlaces() {
            return places;
        }

        public void setPlaces(String places) {
            this.places = places;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
