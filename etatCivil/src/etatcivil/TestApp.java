import java.util.Comparator;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;

public class TestApp {
    public static void main(String[] args) {
        // Création des services
        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();

        // Créer 5 hommes
        Homme homme1 = new Homme("John", "Doe", "123456789", "123 Main St", new Date(1990, 5, 15));
        Homme homme2 = new Homme("James", "Smith", "987654321", "456 Elm St", new Date(1985, 3, 22));
        Homme homme3 = new Homme("Robert", "Brown", "555123789", "789 Oak St", new Date(1978, 7, 5));
        Homme homme4 = new Homme("Michael", "Johnson", "111222333", "321 Pine St", new Date(1980, 11, 19));
        Homme homme5 = new Homme("William", "Taylor", "444555666", "654 Birch St", new Date(1992, 9, 30));

        // Sauvegarder les hommes dans la base de données via le service
        hommeService.create(homme1);
        hommeService.create(homme2);
        hommeService.create(homme3);
        hommeService.create(homme4);
        hommeService.create(homme5);

        // Créer 10 femmes
        Femme femme1 = new Femme("Anna", "White", "555123456", "789 Oak St", new Date(1993, 2, 17));
        Femme femme2 = new Femme("Maria", "Green", "555654321", "321 Maple St", new Date(1989, 6, 3));
        Femme femme3 = new Femme("Sophia", "Black", "555987654", "456 Cedar St", new Date(1984, 8, 14));
        Femme femme4 = new Femme("Isabella", "Brown", "555111222", "123 Palm St", new Date(1995, 12, 25));
        Femme femme5 = new Femme("Emma", "Johnson", "555333444", "654 Pine St", new Date(1982, 1, 1));
        Femme femme6 = new Femme("Olivia", "Miller", "555444555", "321 Oak St", new Date(1990, 4, 20));
        Femme femme7 = new Femme("Ava", "Davis", "555555666", "987 Birch St", new Date(1992, 11, 18));
        Femme femme8 = new Femme("Mia", "Martinez", "555666777", "111 Willow St", new Date(1987, 5, 30));
        Femme femme9 = new Femme("Amelia", "Harris", "555777888", "222 Maple St", new Date(1994, 9, 14));
        Femme femme10 = new Femme("Charlotte", "Wilson", "555888999", "333 Cedar St", new Date(1991, 10, 8));

        // Sauvegarder les femmes dans la base de données via le service
        femmeService.create(femme1);
        femmeService.create(femme2);
        femmeService.create(femme3);
        femmeService.create(femme4);
        femmeService.create(femme5);
        femmeService.create(femme6);
        femmeService.create(femme7);
        femmeService.create(femme8);
        femmeService.create(femme9);
        femmeService.create(femme10);

        // Créer des mariages avec date de début, date de fin et nombre d'enfants
        Mariage mariage1 = new Mariage(homme1, femme1, new Date(2010, 6, 20), new Date(2020, 6, 20), 2);
        Mariage mariage2 = new Mariage(homme2, femme2, new Date(2008, 5, 13), new Date(2018, 5, 13), 3);
        Mariage mariage3 = new Mariage(homme3, femme3, new Date(2009, 7, 19), new Date(2019, 7, 19), 1);
        Mariage mariage4 = new Mariage(homme4, femme4, new Date(2011, 9, 25), new Date(2021, 9, 25), 2);
        Mariage mariage5 = new Mariage(homme5, femme5, new Date(2012, 10, 30), new Date(2022, 10, 30), 3);
        Mariage mariage6 = new Mariage(homme1, femme6, new Date(2015, 4, 15), new Date(2025, 4, 15), 1);
        Mariage mariage7 = new Mariage(homme2, femme7, new Date(2013, 11, 18), new Date(2023, 11, 18), 0);
        Mariage mariage8 = new Mariage(homme3, femme8, new Date(2014, 6, 5), new Date(2024, 6, 5), 4);
        Mariage mariage9 = new Mariage(homme4, femme9, new Date(2016, 7, 12), new Date(2026, 7, 12), 1);
        Mariage mariage10 = new Mariage(homme5, femme10, new Date(2017, 8, 9), new Date(2027, 8, 9), 2);

        // Sauvegarder les mariages dans la base de données via le service
        mariageService.create(mariage1);
        mariageService.create(mariage2);
        mariageService.create(mariage3);
        mariageService.create(mariage4);
        mariageService.create(mariage5);
        mariageService.create(mariage6);
        mariageService.create(mariage7);
        mariageService.create(mariage8);
        mariageService.create(mariage9);
        mariageService.create(mariage10);

        // Afficher la liste des femmes
        List<Femme> femmes = femmeService.getAll();
        femmes.forEach(f -> System.out.println(f.getNom()));


        // Afficher les épouses d’un homme donné entre deux dates
        Homme homme = hommeService.getById(1); // Exemple pour l'homme avec l'ID 1
        List<Femme> epouses = hommeService.findEpousesBetweenDates(homme, new Date(2000, 1, 1), new Date(2020, 12, 31));
        System.out.println("Épouses de l'homme " + homme.getNom() + " entre les dates spécifiées :");
        epouses.forEach(e -> System.out.println(e.getNom()));

        // Afficher le nombre d’enfants d’une femme entre deux dates
        Femme femme = femmeService.getById(1); // Exemple pour la femme avec l'ID 1
        int nbEnfants = femmeService.countEnfantsBetweenDates(femme, new Date(2000, 1, 1), new Date(2020, 12, 31));
        System.out.println("Nombre d'enfants pour la femme " + femme.getNom() + " entre les dates spécifiées : " + nbEnfants);

        // Afficher la liste des femmes mariées deux fois ou plus
        List<Femme> femmesMultiples = femmeService.findFemmesWithMultipleMariages();
        System.out.println("Femmes mariées deux fois ou plus :");
        femmesMultiples.forEach(f -> System.out.println(f.getNom()));

        // Afficher les hommes mariés par quatre femmes entre deux dates
        long hommesMaries = hommeService.countHommesMarriedByFourFemmesBetweenDates(new Date(2000, 1, 1), new Date(2020, 12, 31));
        System.out.println("Nombre d'hommes mariés par 4 femmes entre les dates spécifiées : " + hommesMaries);

        // Afficher les mariages d’un homme donné
        System.out.println("Mariages de l'homme " + homme.getNom() + " :");
        hommeService.afficherMariagesHomme(homme);
    }
}
