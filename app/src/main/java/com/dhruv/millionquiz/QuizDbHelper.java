package com.dhruv.millionquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dhruv.millionquiz.QuizContract.CategoriesTable;
import com.dhruv.millionquiz.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MillionQuiz.db";
    private static final int DATABASE_VERSION = 3;
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";


        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "
                + QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_HINT + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Sports");
        addCategory(c2);
        Category c3 = new Category("Indian History");
        addCategory(c3);
        Category c4 = new Category("Geography");
        addCategory(c4);
        Category c5 = new Category("World Politics");
        addCategory(c5);
        Category c6 = new Category("Indian Politics");
        addCategory(c6);
        Category c7 = new Category("Science");
        addCategory(c7);
        Category c8 = new Category("Math");
        addCategory(c8);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {


//Programming
        Question p1 = new Question("Programming Question : A", "A", "B", "C", "D", 1, "Apple", 1);
        addQuestion(p1);

//Sports
        Question s1 = new Question("When and Where was the first Cricket World Cup organized ?",
                "1973, Sri Lanka", "1972, India", "1975, England", "1967, West Indies",
                3,
                "The country with London as its Capital",
                Category.SPORTS);
        addQuestion(s1);

        Question s2 = new Question("Which national team won the 2018 FIFA World Cup held in Russia ?",
                "France", "England", "Croatia", "Belgium",
                1,
                "Kylian Mbappe is part of this national team",
                Category.SPORTS);
        addQuestion(s2);

        Question s3 = new Question("Who was the top scorer in 2018 FIFA World Cup ?",
                "Kylian Mbappe", "Harry Kane", "Cristiano Ronaldo", "Romelu Lukaku",
                2,
                "Plays for England national team",
                2);
        addQuestion(s3);

        Question s4 = new Question("The 2018 FIFA World was held in which country ?",
                "Croatia", "South Africa", "Brazil", "Russia",
                4,
                "Largest Country in the World",
                2
        );
        addQuestion(s4);

        Question s5 = new Question("Who is the youngest player in the ICC Cricket World Cup 2019?",
                " Jonny 'Blaze' Bairstow", "Yuzvendra chahal", "Mujeeb ur Rahman", "Kagiso Rabada",
                3,
                "plays for Afganishtan national cricket team",
                2);
        addQuestion(s5);

        Question s6 = new Question("Which Indian cricketer had won the “Man of the Match” award in the final of the ICC World Cup 1983?",
                "Kapil Dev", "Sunil Gavaskar", "Ravi Shastri", "Mohinder Amarnath",
                4,
                "His nickname is Jimmy",
                2);
        addQuestion(s6);

        Question s7 = new Question("In which country is basketball a major sport?",
                "Canada", "United States of America", "United Kingdom", "Australia",
                2,
                " a nation that is the world's most dominant economic and military power.",
                2);
        addQuestion(s7);

        Question s8 = new Question("When were the first Commonwealth Games held?",
                "1930", "1934", "1943", "1948",
                1,
                "193...",
                2);
        addQuestion(s8);

        Question s9 = new Question("The term 'Butterfly Stroke' is referred to in which sport?",
                "Wrestling", "Volleyball", "Tennis", "Swimming",
                4,
                "The sport takes place in pools or open water.",
                2);
        addQuestion(s9);

        Question s10 = new Question("In which game the term 'Putting' is used?",
                "Chess", "Hockey", "Golf", "Billiards",
                3,
                "players use various clubs to hit balls into a series of holes",
                2);
        addQuestion(s10);

        Question s11 = new Question("Thomas Cup is related to which sport ?",
                "Tennis", "Cricket", "Basketball", "Badminton",
                4,
                "a racquet sport played using racquets to hit a shuttlecock across a net.",
                2);
        addQuestion(s11);

        Question s12 = new Question("Ryder Cup is related to which sport?",
                "Football", "Badminton", "Golf", "Cricket",
                3,
                "players use various clubs to hit balls into a series of holes",
                2);
        addQuestion(s12);

        Question s13 = new Question("Which was the first country to host the Asian games?",
                "India", "Japan", "China", "Korea",
                1,
                "largest democracy in the world",
                2);
        addQuestion(s13);

        Question s14 = new Question("Nine time Wimdledon Champion of Women's Singles Match in Tennis is..",
                "Iva Majoli", "Martina Navratilova", "Mary Joe Fernandaz", "Jana Novotna",
                2,
                "also won 18 Grand Slam singles titles, 31 Grand Slam women's doubles championships",
                2);
        addQuestion(s14);

        Question s15 = new Question(" 'Free throw' is associated with which sport ?",
                "Football", "Hockey", "Volleyball", "Basketball",
                4,
                "Michael Jordan is associated with this sport",
                2);
        addQuestion(s15);

        Question s16 = new Question("When did Sachin Tendulkar make his first Test debut?",
                "10 October, 1988", "15 November, 1989", "20 October 1989", "11 November, 1988",
                2,
                "Sachin Tendulkar has made his first test debut against Pakistan in Karachi on 15 November 1989.",
                2);
        addQuestion(s16);

        Question s17 = new Question("Sachin Tendulkar has a record of playing how many ODIs in a row?",
                "180", "183", "185", "188",
                3,
                "more than 184",
                2);
        addQuestion(s17);

        Question s18 = new Question("Against which country, Sachin Tendulkar scored his first test century?",
                "England", "Pakistan", "Australia", "New Zealand",
                1,
                "A country famous for David Beckham, Fish and Chips, Big Ben, Red Buses, black cabs, Oasis, Blur and the Beatles",
                2);
        addQuestion(s18);

        Question s19 = new Question("Where was the first edition of ICC Men's T20 Cricket World Cup held?",
                "India", "England", "South Africa", "None of the above",
                3,
                "Nelson Mandela served as President of this country from 1994 to 1999",
                2);
        addQuestion(s19);

        Question s20 = new Question(" Which of the following country has not won a single title of ICC Men's T20 Cricket World Cup?",
                "Australia", "Pakistan", "England", "Sri Lanka",
                1,
                "this country has won the most ICC Cricket World Cups of the 50 over format.",
                2);
        addQuestion(s20);

        Question s21 = new Question("Which country has won the very first edition of the ICC Men's T20 Cricket World Cup?",
                "Sri Lanka", "West Indies", "Pakistan", "India",
                4,
                "arch-rival of pakistan cricket team",
                2);
        addQuestion(s21);


        Question s22 = new Question("Which of the following team has not won a single title of ICC Women's T20 World Cup so far?",
                "England", "West Indies", "India", "Australia",
                3,
                "Land of gurus",
                2);
        addQuestion(s22);

        Question s23 = new Question(". Which country has won the ICC Women's T20 World Cup the most times?",
                "West Indies", "England", "Australia", "None of the above",
                3,
                "Winners of 2010, 2012, 2014 and 2018",
                2);
        addQuestion(s23);

        Question s24 = new Question("Where was the first ICC Women's T20 World Cup held?",
                "Australia", "England", "West Indies", "Sri Lanka",
                2,
                "A country famous for David Beckham, Fish and Chips, Big Ben, Red Buses, black cabs, Oasis, Blur and the Beatles",
                2);
        addQuestion(s24);

        Question s25 = new Question("When did India win the ICC Women's T20 World Cup title?",
                "2018", "2014", "2010", "Never",
                4,
                "largest democracy in the world",
                2);
        addQuestion(s25);

        Question s26 = new Question("Who was the captain of India in ICC Women's T20 World Cup, 2020?",
                "Mithali Raj", "Smriti Mandhana", "Harmanpreet Kaur", "Poonam Yadav",
                3,
                "ODI shirt no‎: ‎7 \nBowling‎ Style: ‎Right-arm offbreak",
                2);
        addQuestion(s26);

        Question s27 = new Question("Where was ICC Women's T20 World Cup 2020 final played?",
                "Melbourne", "Sydney", "London", "Hambantota",
                1,
                "the coastal capital of the southeastern Australian state of Victoria.",
                2);
        addQuestion(s27);

        Question s28 = new Question("Who is the winner of the US Open 2019 Men's title?",
                "Roger Federer", "Rafael Nadal", "Daniil Medvedev", "Novak Djokovic",
                2,
                "This is his 19th Grand Slam singles titles.",
                2);
        addQuestion(s28);

        Question s29 = new Question("Who got FIFA Best Player 2019 Award?",
                "Neymar", "Luka Modric", "Crisitiano Ronaldo", "Lionel Messi",
                4,
                "It was his 6th time winning this award.",
                2);
        addQuestion(s29);

        Question s30 = new Question("Pankaj Advani recently won his 22nd World Billiards Title 2019. This tournament was held in.. ",
                "Malaysia", "Myanmar", "India", "Russia",
                2,
                "Southeast Asian nation of more than 100 ethnic groups, bordering India, Bangladesh, China, Laos and Thailand.",
                2);
        addQuestion(s30);

        Question s31 = new Question(" Who has won the Australian Open 2020 men's singles title?",
                "Novak Djokovic", "Rafael Nadal", "Dominic Thiem", "Roger Federer",
                1,
                "It was his eighth Australian Open title on Sunday as he beat Dominic Thiem in a five-set thriller.",
                2);
        addQuestion(s31);

        Question s32 = new Question("Which of the following country has not won the ICC Cricket World Cup (50 over format) title so far?",
                "England", "South Africa", "New Zealand", "All of the above",
                4,
                "All of the above",
                2);
        addQuestion(s32);

        Question s33 = new Question("When was the First Wimbledon held?",
                "20 July, 1877", "19 July, 1870", "1 June, 1877", "20 July, 1870",
                1,
                "1877",
                2);
        addQuestion(s33);

        Question s34 = new Question("Roger Federer has won how many Grand Slams?",
                "15", "19", "20", "18",
                3,
                "20",
                2);
        addQuestion(s34);

        Question s35 = new Question("The famous Indian Express was a team",
                "Leander Paes and Mahesh Bhupati", "Yuki Bhambri and RohanBoppana", " Leander Paes and Yuki Bhambri", "Mahesh Bhupatiand RohanBoppana",
                1,
                "Leander Paes and...",
                2);
        addQuestion(s35);

        Question s36 = new Question("Which of the following terms is used in the game of Lawn Tennis?",
                "Jockey", "Scoop", "Deuce", "Punter",
                3,
                "the score of 40 all in a game, at which each player needs two consecutive points to win the game.",
                2);
        addQuestion(s36);

        Question s37 = new Question("Which of the following terms is not used in the game of Lawn Tennis?",
                "Gambit", "Ace", "Smash", "Back hand drive",
                1,
                "(in chess) an opening move in which a player makes a sacrifice,",
                2);
        addQuestion(s37);

        Question s38 = new Question(" Which of the following is the highest governing body of lawn tennis?",
                "TAW: Tennis Association of the World", "WTA: World Tennis Authority", "WTF: World Tennis Federation", "ITF: International Tennis Federation",
                4,
                "The governing body of world tennis, wheelchair tennis, and beach tennis. ________ Tennis Federation was founded in 1913",
                2);
        addQuestion(s38);

        Question s39 = new Question("In the Lawn Tennis while serving the serve always has to fall in the.............",
                "Opposite box", "Diagonal Opposite box", "Any box", "Anywhere in opposite court",
                2,
                "Diagonal Opposite box",
                2);
        addQuestion(s39);

        Question s40 = new Question("The first French open was organized in?",
                "1892", "1891", "1861", "1859",
                2,
                "189_",
                2);
        addQuestion(s40);

        Question s41 = new Question("32nd Olympic games will be held in..",
                "Argentina", "Brazil", "Qatar", "Japan",
                4,
                "The birthplace of sushi",
                2);
        addQuestion(s41);

        Question s42 = new Question("Where were first Olympic Games held?",
                "Atlanta", "Mexico", "Athens", "Sydney",
                3,
                "The Capital of Greece",
                2);
        addQuestion(s42);

        Question s43 = new Question("In which Olympic Games, Indian hockey team won Gold medal?",
                "9th", "10th", "11th", "12th",
                1,
                "9th",
                2);
        addQuestion(s43);

        Question s44 = new Question("Formula 1 is sanctioned by which authority?",
                "World Motorsport Association", "International Federation of Motorsports", " Association of Motorsport Racing", "Federation of International Automobile",
                4,
                "FIA",
                2);
        addQuestion(s44);

        Question s45 = new Question("As of 2018, the global television audience of F1 is believed to be..",
                "425 million", "400 million", "300 million", "429 million",
                1,
                "42_ million",
                2);
        addQuestion(s45);

        Question s46 = new Question("Which track held the first F1 race?",
                "Buddha Circuit", "Silverstone Circuit", "Albert Park", "Shanghai International Circuit",
                2,
                "A motor racing circuit in England which is the current home of the British Grand Prix",
                2);
        addQuestion(s46);

        Question s47 = new Question("What does the Red Flag during a F1 race indicate?",
                "Race Stopped", "Accident up-ahead", "Safety car is out on track", "Someone won the race",
                1,
                "Red indicates stop",
                2);
        addQuestion(s47);

        Question s48 = new Question("After finishing of a F1 race, points are given to the… ?",
                "Driver", "Race Team", "Race Team and Driver", "Owners of the team",
                3,
                "team and driver",
                2);
        addQuestion(s48);

        Question s49 = new Question(" How many cars are allowed in track by any one race team in F1 race?",
                "1", "2", "3", "4",
                2,
                "2",
                2);
        addQuestion(s49);

        Question s50 = new Question("An international governing body that governs, manages and promotes association football, futsal and beach soccer is..",
                "FIFA", "IFA", "UIFA", "FIFF",
                1,
                "International Federation of Association Football",
                2);
        addQuestion(s50);

        Question s51 = new Question("Champions League played in Europe is organised and managed by which Organisation?",
                "FIFA", "UEFA", "CONCAF", "FA",
                2,
                "the governing body of Football Federations from Europe",
                2);
        addQuestion(s51);

        Question s52 = new Question("Which team won 2014 FIFA World Cup?",
                "Germany", "France", "Argentina", "Spain",
                1,
                "Manuel Neuer is the captain of this team",
                2);
        addQuestion(s52);

        Question s53 = new Question("Who scored the winning goal in 2014 FIFA World Cup?",
                "Mario Gotze", "Lionel Messi", "Argen Robben", "Cristiano Ronaldo",
                1,
                "A German professional footballer who plays as an attacking midfielder for Borussia Dortmund",
                2);
        addQuestion(s53);

        Question s54 = new Question("Who won the FIFA Puskas Award 2019 ?",
                "Lionel Messi", "Mohamed Salah", "Juan Fernando Quintero", "Daniel Zsori",
                4,
                "Hungarian professional footballer who plays as a forward for Fehérvár.",
                2);
        addQuestion(s54);

        Question s55 = new Question("Which player holds the record for most goals in World Cup finals?",
                "Ronaldo", "Lionel Messi", "Miroslav Klose", "Timo Werner",
                3,
                "A German footballer who played as a striker.",
                2);
        addQuestion(s55);

        Question s56 = new Question("Who is the football player with most Ballon d'Or awards",
                "Cristiano Ronaldo", "Lionel Messi", "Ronaldo(Brazil)", "Luka Modric",
                2,
                "In 2019 he won the award for a sixth time in his career.",
                2);
        addQuestion(s56);

        Question s57 = new Question("Which cricketer has maximum sixes in International Cricket ?",
                "Chris Gayle", "Shahid Afridi", "Sachin Tendulkar", "Rohit Sharma",
                1,
                "Jamaican cricketer who plays international cricket for the West Indies.",
                2);
        addQuestion(s57);

        Question s58 = new Question("Who is highest individual scorer in ODI history?",
                "Martin Guptil", "Virender Sehwag", "Chris Gayle", "Rohit Sharma",
                4,
                "captains Mumbai Indians in the Indian Premier League as a right-handed batsman",
                2);
        addQuestion(s58);

        Question s59 = new Question("Which cricketer has scored most centuries in first-class cricket?",
                "Lane Hutton", "Wally Hammond", "Jack Hobbs", "Sachin Tendulkar",
                3,
                " Known as \"The Master\", he is regarded by critics as one of the greatest batsmen in the history of cricket. ",
                2);
        addQuestion(s59);

        Question s60 = new Question("Which cricketer has scored fastest century in ODI cricket?",
                "Vivian Richards", "Corey Anderson", "Shahid Afridi", "AB De Villiers",
                4,
                "He is a South African cricketer.",
                2);
        addQuestion(s60);

        Question s61 = new Question("The term 'Beamer' is associated with",
                "Football", "Cricket", "Hockey", "Chess",
                2,
                "a bat-and-ball game played between two teams of eleven players",
                2);
        addQuestion(s61);

        Question s62 = new Question("Bull Fighting is the national game of",
                "Spain", "Portugal", "Hungary", "Poland",
                1,
                "A favorite destination especially for the food-loving, wine-chugging, and siesta-loving people.",
                2);
        addQuestion(s62);

        Question s63 = new Question("Narain Karthikeyan is the sports man int the field of",
                "Formula one car racing", "Shooting", "Chess", "Golf",
                1,
                "He was the first participant from India in this sport.",
                2);
        addQuestion(s63);

        Question s64 = new Question("Which of the following term is not associated with Football?",
                "Penalty Kick", "Free Kick", "Penalty Stroke", "Offside",
                3,
                "In field hockey, a penalty stroke is the most severe penalty given. ",
                2);
        addQuestion(s64);

        Question s65 = new Question("Jahangir Khan is famous in which sport",
                "Boxing", "Squash", "Hockey", "Cricket",
                2,
                " a racket and ball sport played in a four-walled court",
                2);
        addQuestion(s65);

        Question s66 = new Question("What is the maximum permitted length of cricket bat?",
                "32\"", "34\"", "36\"", "38\"",
                4,
                "3 feet 2 inches",
                2);
        addQuestion(s66);

        Question s67 = new Question("The term 'pugilist' is associated with",
                "Wrestling", "Boxing", "Javelin Throw", "Sprinter",
                2,
                " a combat sport in which two people, usually wearing protective gloves, throw punches at each other",
                2);
        addQuestion(s67);

        Question s68 = new Question("Fernando Alonso is associated with which sport",
                "Formula One", "Billiards", "Snooker", "Tennis",
                1,
                " the highest class of single-seater auto racing",
                2);
        addQuestion(s68);

        Question s69 = new Question("Which of the International Tennis Tournaments is held on grass court ?",
                "US Open", "French Open", "Wimbledon", "Australia Open",
                3,
                "The oldest tennis tournament in the world",
                2);
        addQuestion(s69);

        Question s70 = new Question("The number of players in each side in Water Polo is",
                "7", "9", "5", "4",
                1,
                "There are six players that play out and one goalkeeper.",
                2);
        addQuestion(s70);

        Question s71 = new Question("The name 'Geet Sethi' is associated with which sport",
                "Golf", "Billiards", "Lawn Tennis", "Cricket",
                2,
                "A game played on a rectangular table with a designated number of small balls and a long stick called a cue.",
                2);
        addQuestion(s71);

        Question s72 = new Question("Serena Williams is one of the top ranked sportswomen of",
                "Badminton", "Shooting", "Tennis", "Chess",
                3,
                "Player uses a racket to strike a ball over or around a net into opponent's court",
                2);
        addQuestion(s72);

        Question s73 = new Question("The term 'Pitcher' is associated with which sport",
                "Wrestling", "Boxing", "Baseball", "Basketball",
                3,
                " a bat-and-ball game played between two opposing teams who take turns batting and fielding. ",
                2);
        addQuestion(s73);

        Question s74 = new Question("Which of the following terms is used in Cricket?",
                "Goal", "Love", "Penalty Stroke", "LBW",
                4,
                "Leg before wicket is one of the ways in which a batsman can be dismissed in the sport of cricket.",
                2);
        addQuestion(s74);

        Question s75 = new Question("In which year did Maradona score a goal with his hand?",
                "1986", "1983", "1990", "1991",
                1,
                "198_",
                2);
        addQuestion(s75);

        Question s76 = new Question("Who was the first player to win five consecutive Wimbledon tennis titles?",
                "Roger Federer", "Bjorn Borg", "Andre Agassi", "Arthur Ashe",
                2,
                "A Swedish former world No. 1 tennis player.",
                2);
        addQuestion(s76);

        Question s77 = new Question("Who is famous for 'bending' a football?",
                "David Beckham", "Zinedine Zidane", "Ronaldo", "Luis Figo",
                1,
                "Bend It Like Beckham",
                2);
        addQuestion(s77);


        Question s78 = new Question("Which year saw the inaugural season of Formula 1?",
                "1960", "1967", "1950", "1953",
                3,
                "195_",
                2);
        addQuestion(s78);


//History

        Question h1 = new Question("Which of the following is the oldest Veda ?", "Samaveda", "Yajurveda", "Rigveda", "Atharvaveda", 3, "Contains various hymns for praying to Vedic Gods such as Agni (Fire God), Indra (The lord of Heavens), Mitra, Varuna (Water God), Surya (Sun God)", 3);
        addQuestion(h1);


        Question h2 = new Question("On which of the following systems of Hindu Philosophy , Shankaracharya wrote commentary in 9th century AD?",
                "Sankhya", "Vaisheshika", "Yoga", "Uttarmimansa",
                4,
                "Known as  the Vedanta",
                3);
        addQuestion(h2);

        Question h3 = new Question("Which king started the organization of Kumbh fair at Allahabad?",
                "Harshavardhana", " Dhruvasena Ii", "Narshimhvarman", "Akabar",
                1,
                " son of Prabhakarvardhana ",
                3);
        addQuestion(h3);

        Question h4 = new Question("Who was the first Indian ruler who had territory outside India?",
                "Ashoka", "Chandragupta Maurya", "Kanishka", "Huvishka",
                3,
                "an emperor of the Kushan dynasty in the second century",
                3);
        addQuestion(h4);

        Question h5 = new Question("Who among the following was worshipped during Early Vedic Civilization?",
                " Varuna", "Indra", "Surya", "All the above",
                4,
                " all of them were worshipped",
                3);
        addQuestion(h5);

        Question h6 = new Question("Where were the hymns of Rigveda composed?",
                "Punjab", "Gujrat", " Rajasthan", "Uttar Pradesh",
                1,
                "Land of gurus",
                3);
        addQuestion(h6);

        Question h7 = new Question("The traces of Janapadas and Mahajanpadas are found in___?",
                "Vedic text", "Buddha text", "Jaina text", "All of the above",
                4,
                "All of the above",
                3);
        addQuestion(h7);


        Question h8 = new Question("The capital of Kosala during the Mahajanapada period was",
                "Shravasti", "Shuktimati", "Kaushambi", "Indraprastha",
                1,
                "a city of ancient India and one of the six largest cities in India during Gautama Buddha's lifetime.",
                3);
        addQuestion(h8);


        Question h9 = new Question("Bimbisara was the founder of which of the following dynasties?",
                "Shunga", "Maurya", "Haryanka", "Nanda",
                3,
                "Haryanka Dynasty",
                3);
        addQuestion(h9);


        Question h10 = new Question("A Janapadin was the ___ of a Janapada.",
                "Servant", "Minister", "Army General", "Ruler",
                4,
                " leader of a country , a queen ,a sultan ",
                3);
        addQuestion(h10);


        Question h11 = new Question("Who discovered Harappa site?",
                "Rakhal Das Bannerji", "Rai Bahadur Daya Ram Sahni", "B.K. Thapar", "Mackey",
                2,
                "Daya Ram Sahni",
                3);
        addQuestion(h11);


        Question h12 = new Question("In the Vedic age, who was the head of “Grama”?",
                "Kulapa", "Gramini", "Vispati", "Gopati",
                2,
                "Gramini",
                3);
        addQuestion(h12);


        Question h13 = new Question(" The local name of Mohenjodaro is",
                "Mound of the Dead Men", "Mound of the living", "Mound of the survivor", "Mound of the tree",
                1,
                "Mohenjo-daro, the modern name for the site, has been variously interpreted as \"Mound of the Dead Men\" in Sindhi",
                3);
        addQuestion(h13);


        Question h14 = new Question("Who were the immediate successors of the Mauryas in Magadha?",
                "Pandyas", "Shungas", "Kushanas", "Satvahanas",
                2,
                "The Shunga Empire was established by Pushyamitra Shunga, after the fall of the Maurya Empire.",
                3);
        addQuestion(h14);


        Question h15 = new Question("Which among the following is the oldest dynasty?",
                "Maurya", "Gupta", "Kushav", "Kanav",
                1,
                "India's one of the oldest dynasty was Maurya Empire (322 BCE- 185 BCE).",
                3);
        addQuestion(h15);


        Question h16 = new Question("Aryabhata, believed to have been born in the 5th century AD, was a most renowned scholar of:",
                "Biology", "Astronomy", "Medicine", "Physiology",
                2,
                "He suggested the heliocentric theory which proved that the sun is located in the centre of the solar system and all the planets revolve around it.",
                3);
        addQuestion(h16);


        Question h17 = new Question("Who of the following was a contemporary of Alexander the Great?",
                "Bimbisara", " Chandragupta Maurya", " Ashoka", "Pushyamitra Sunga",
                2,
                "The founder of the Maurya Empire in ancient India.",
                3);
        addQuestion(h17);


        Question h18 = new Question("Who was the last great ruler of Gupta dynasty?",
                "Skandagupta", "Chandragupta I", "Chandragupta II", "kumaragupta",
                1,
                "Son and successor of Kumaragupta I is generally considered to be the last of the great Gupta rulers.",
                3);
        addQuestion(h18);


        Question h19 = new Question("Which of the following is incorrectly matched (in Gupta administration)?",
                "Bhukti-Province", "Vishaya-State", "Vithika-City", "Gram-Village",
                2,
                "Districts were called vishayas",
                3);
        addQuestion(h19);


        Question h20 = new Question("Who is the first foreigner to visit India?",
                "Hiuen-Tsang", "I Ching", "Megasthenese", "Fahien",
                3,
                "Megasthenes is the very first foreigner to be visited India from Greek.",
                3);
        addQuestion(h20);


        Question h21 = new Question("Who was the first Indo-Greek king, who became Buddhist?",
                "Antiochus II", " Apollodotus I", "Apollodotus II", "Menander II",
                4,
                "The coins of Menander II bear the mention \"Menander the Just\", and \"King of the Dharma\" in Kharoshti, suggesting that he adopted the Buddhist faith.",
                3);
        addQuestion(h21);


        Question h22 = new Question("Who wrote Panchatantra?",
                "Kalidas", "Vishnu Sharma", "Chanakya", "Nagarjun",
                2,
                "Vishnu Sharma",
                3);
        addQuestion(h22);


        Question h23 = new Question("Which of the following is not a religious text?",
                "Rigveda", "Sangam", "Upanishad", "None of the above",
                2,
                "Sangam is the earliest known literature of South India",
                3);
        addQuestion(h23);


        Question h24 = new Question("Kalinga was situated between?",
                "Godavari & Krishna", "Mahanadi & Godavari", "Mahanadi & Krishna", "Krishna & Kaveri",
                2,
                "Mahanadi and _________",
                3);
        addQuestion(h24);


        Question h25 = new Question("Which of the following is known as a book of Melody?",
                "Samaveda", "Rigveda", "Atharvaveda", "Yajurveda",
                1,
                "The Samaveda is the Veda of melodies and chants.",
                3);
        addQuestion(h25);


        Question h26 = new Question("Economy of Vedic period was based on?",
                "Pastureland", "Agriculture", "Mining", "Cotton Cultivation",
                2,
                "The process of producing food, feed, fiber and many other desired products by the cultivation of certain plants",
                3);
        addQuestion(h26);


        Question h27 = new Question("What is the ancient name of river Sutlej/Satluj?",
                "Asikini", "Parusni", "Vipasa", "Shutudri",
                4,
                "Shutudri",
                3);
        addQuestion(h27);


        Question h28 = new Question("Who was the first teacher of Gautama Buddha?",
                "Alara Kalama", "Kalidas", "Vishwamitra", "Vashistha",
                1,
                "Alara Kalama a teacher of ancient meditation.He was the first teacher of Gautama Buddha",
                3);
        addQuestion(h28);


        Question h29 = new Question("Which symbol shows renunciation of Gautam Buddha?",
                "Lotus", "Bull", "Elephant", "Horse",
                4,
                "The symbol ‘Horse’ signifies the renunciation of Buddha’s life.",
                3);
        addQuestion(h29);


        Question h30 = new Question("Who was the founder of Pataliputra?",
                "Bimbisar", "Ajatashatru", "Udayin", "Kanishka",
                3,
                "Udayin, the son and successor of the Haryanka king Ajatashatru, laid the foundation of the city of Pataliputra ",
                3);
        addQuestion(h30);


        Question h31 = new Question("Which Mauryan king holds the title of Amitraghata?",
                "Bimbisar", "Chandragupta Maurya", "Bindusara", "Ashoka",
                3,
                "The son of Chandragupta Maurya and father of Asoka the great.",
                3);
        addQuestion(h31);

        Question h32 = new Question(" Which of the following inscriptions is found in purest Sanskrit?",
                "Ruminidei", "Junagarh", "Kalsi", "Patliputra",
                2,
                "The Junagarh rock inscription is the first long inscription in fairly standard Sanskrit that has survived into the modern era.",
                3);
        addQuestion(h32);

        Question h33 = new Question("Who is known as the Napoleon of India?",
                "Srigupta", "Chandragupta", "Samudragupta", "Devicharangupta",
                3,
                "Samudragupta (335-375 AD) of the Gupta dynasty is known as the Napoleon of India.",
                3);
        addQuestion(h33);

        Question h34 = new Question("Panchasiddhantika, written by Varahamihir is based on?",
                "Mathematics", "Science", "Astrology", "Astronomy",
                4,
                "Panchasiddhantika means five astronomical canons.",
                3);
        addQuestion(h34);

        Question h35 = new Question("Bagh painting of Gupta Empire was found in which of the following Indian states?",
                "Bihar", "Madhya Praadesh", "Chattisgarh", "Maharashtra",
                2,
                "Bagh Caves are in Dhar District of Madhya Pradesh.The paintings in these caves were engraved in the time period of 500 AD to 700 AD.",
                3);
        addQuestion(h35);

        Question h36 = new Question("Kailasha temple of Ellora was built by?",
                "Krishna I", "Krishna II", "Ramakrishna I", "Ramakrishna II",
                1,
                "Krishna I ",
                3);
        addQuestion(h36);

        Question h37 = new Question("Prithivyah Pratham Veer was the title of?",
                "Samudragupta", "Rajendra I", "Amoghavarsha", "Gautamiputra Shatkarni",
                1,
                "Samudragupta",
                3);
        addQuestion(h37);

        Question h38 = new Question("At which Indus Valley site the Dockyard was found?",
                "Ropar", "Lothal", "Kalibangan", "Banawali",
                2,
                "Lothal is the only Indus site where an artificial brick dockyard was found",
                3);
        addQuestion(h38);

        Question h39 = new Question("Who was the founder of Vikramshila Vihar?",
                "Dharmapala", "Gopala", "Nagpal", "Mahipala",
                1,
                "Vikramashila was founded by Pāla king Dharmapala in the late 8th or early 9th century.",
                3);
        addQuestion(h39);

        Question h40 = new Question("The accounts of Kalinga war are depicted by:",
                "Rock edict XIII", "Ruminidei", "Kalsi", "Junagarh",
                1,
                "The accounts of Kalinga war was depicted in rock edict 13.",
                3);
        addQuestion(h40);

        Question h41 = new Question("In Jain literature, which of the following Tirthankar is also known as Prajapati, Adibrahma and Aadinath?",
                "Rishabhnath", "Ajitnath", "Sambhav nath", "Abhinindan nath",
                1,
                "Rishabhanatha - the first Tirthankara (ford maker) in Jainism ,is also known as Prajapati, Adibrahma and Aadinath",
                3);
        addQuestion(h41);

        Question h42 = new Question("Who was the founder of Sankhya system of Indian philosophy?",
                "Kapila Muni", "Gautama Muni", "Jaimini", "Kanada",
                1,
                "Kapila Muni is traditionally credited as a founder of the Sankhya System",
                3);
        addQuestion(h42);

        Question h43 = new Question("Who is known as the \"slave of a slave\"?",
                "Muhammad bin Oasim", "Mahmud of Ghazni", "Iltutmish", "Outbuddin Aibak",
                3,
                "Iltutmish, a “slave of a slave” is regarded by several historians as the real founder and consolidator of the slave Dynasty",
                3);
        addQuestion(h43);

        Question h44 = new Question("Who was the first Sultan of Delhi to issue regular currency and to declare Delhi as the capital of his empire?",
                "Balban", "Aram Shah", "Nasiruddin Mahmud", "Iltutmish",
                4,
                "Iltutmish was the first Sultan of Delhi to issue regular currency and declare Delhi as the capital of his empire. ",
                3);
        addQuestion(h44);

        Question h45 = new Question("Which Sultan of Delhi died while playing the chaugan (polo)?",
                "Qutbuddin Aibak", "Ghiyasuddin Balban", "Shamsuddin IItutmish", "Nasiruddin Mahmud",
                1,
                "Qutbuddin Aibak died as a result of a fall from his horse while playing Chaugan.",
                3);
        addQuestion(h45);

        Question h46 = new Question("Which one of the following is not correctly matched?",
                "Qutbuddin-Adhai din ka Jhompra", "Iltutmish-Qutab Minar", "Alauddin- Hauz-i-Khas", "Firuz Tughlaq-Tughlaqabad",
                4,
                "The Tughlaqabad Fort was built by the founder of the Tughlaq Dynasty, Ghiyas-ud-din-Tughlaq",
                3);
        addQuestion(h46);

        Question h47 = new Question("Who is the author of \"Shahnama\"?",
                "Utbi", "Firdausi", "Hasan Nizami", "AI-Beruni",
                2,
                "Firdausi was a Persian poet and the author of Shahnameh, which is one of the world's longest epic poems created by a single poet.",
                3);
        addQuestion(h47);

        Question h48 = new Question("What was the unique system developed by the Mughals?",
                "Centralised autocracy", "Ryotwari settlement", "Mansabdari system", "Local responsibilities for crime detection",
                3,
                "Mansabari system-a mansabdar or noble was granted the rights to hold a jaguar which meant revenue assignments for services rendered by them",
                3);
        addQuestion(h48);

        Question h49 = new Question("From where the Mansabdari system was borrowed?",
                "Afghanistan", "Turkey", "Mongolia", "Persia",
                3,
                "Its capital, Ulaanbaatar, centers around Chinggis Khaan (Genghis Khan) Square.",
                3);
        addQuestion(h49);

        Question h50 = new Question("Who among the following Sultans of Delhi introduced the token currency?",
                "Balban", "Alauddin Khalji", "Muhammad bin Tughlaq", "Firuz Tughlaq",
                3,
                "oken Currency System was introduced by Muhammad Tughlaq in India",
                3);
        addQuestion(h50);

        Question h51 = new Question("Tax on plunder during war in the Sultanate period was known as",
                "Kharaj", "Jizya", "Khums", "Zakat",
                3,
                "Khums was a tax taken on plunder.",
                3);
        addQuestion(h51);

        Question h52 = new Question(" Which one of the following sequences represents the correct chronological order?",
                "Shahji, Shivaji, Rajaram, Sambhaji", "Shahji, Shivaji, Sambhaji, Rajaram", " Shahji, Sambhaji, Shivaji, Rajaram", "Sambhaji, Shivaji, Shahji, Rajaram",
                2,
                "Shahji, Shivaji, Sambhaji, Rajaram",
                3);
        addQuestion(h52);

        Question h53 = new Question("Who among the following was responsible for making \"Sikhism\" a militant force?",
                "Guru Hargobind Singh Ji", "Guru Teg Bahadur ji", "Guru Gobind Singh ji", "Guru Arjun Singh ji",
                1,
                "Guru Har Govind began the transformation of Sikhs from a peaceful community to militant fighting community",
                3);
        addQuestion(h53);

        Question h54 = new Question("The Buland Darwaza was built to mark Akbar’s conquest of",
                "Awadh", "Malwa", "Gujarat", "Chittor",
                3,
                "the \"Door of victory\", was built by Akbar to commemorate his victory over Gujarat.",
                3);
        addQuestion(h54);

        Question h55 = new Question("Purana Qila at Delhi was built by",
                "Akbar", "Sher Shah", "Humayun", "Shah Jahan",
                2,
                "Construction work of Purana Qila was started by the great Mughal emperor Humayun and was completed by the Afghan ruler Sher Shah.",
                3);
        addQuestion(h55);


        Question h56 = new Question("Which one of the following method of revenue assessment is related to the Vijayanagara Empire?",
                "Chauth", "Ryoywari", "Rae Rekho", "Sardeshmukhi",
                4,
                "'Sardeshmukhi' is an additional 10% tax levied upon the collected 'Chauth'.",
                3);
        addQuestion(h56);


        Question h57 = new Question("Who among the following was Akbar's teacher?",
                "Kabir", "Abul Fazl", "Bairam Khan", "Abdul Latif",
                4,
                "Akbar was very tolerant in his religious views. In his childhood he was impressed by the liberal religious ideas of his teacher, Abdul Latif.",
                3);
        addQuestion(h57);

        Question h58 = new Question("After his coronation Shivaji assumed the title of",
                "Maharaja", "Peshwa", "Chhatrapati", "Samrat",
                3,
                "After his coronation Shivaji assumed the title of Chhatrapati Maharaja",
                3);
        addQuestion(h58);

        Question h59 = new Question("Akbar's mausoleum is situated at",
                "Sasaram", "Sikandra", "Agra", "Delhi",
                2,
                "It is located at Sikandra, in the suburbs of Agra, on the Mathura road",
                3);
        addQuestion(h59);

        Question h60 = new Question("The Upanishads were translated into Persian by",
                "Akbar", "Dara Shikoh", "Shah Jahan", "Jahangir",
                2,
                "the eldest son of Shahjahan.",
                3);
        addQuestion(h60);

        Question h61 = new Question("Tulsi Das composed his Ramacharitamanas during the reign of",
                "Harsha", "Alauddin Khalji", "Akbar", "Krishnadeva Raya",
                3,
                "the third Mughal emperor, who reigned from 1556 to 1605.",
                3);
        addQuestion(h61);

        Question h62 = new Question("which Sikh Guru compiled the \"Guru Granth Sahib ji\"",
                "Guru Nanak Dev Ji", "Guru Arjan Dev Ji", "Guru Har Rai Sahib Ji", "Guru Tegh Bahadur Ji",
                3,
                "the first of the two Gurus martyred in the Sikh faith and the fifth of the ten total Sikh Gurus.",
                3);
        addQuestion(h62);

        Question h63 = new Question("Which of the following ladies wrote an historical account during the Mughal period?",
                "Gulbadan Begum", "Noorjahan Begum", "Jahanara Begum", "Zebun-nissah Begum",
                1,
                "the daughter of Emperor Babur.",
                3);
        addQuestion(h63);


        Question h64 = new Question("Who was the first Indian ruler to organize Haj pilgrimage at the expense of the state?",
                "Alauddin Khilji", "Feroz Tughlaq", "Akbar", "Aurangzeb",
                3,
                "the son of Humayun",
                3);
        addQuestion(h64);

        Question h65 = new Question("Who among the following witnessed the reigns of eight Delhi Sultans?",
                "Ziauddin Barani", "Shams-i-siraj Afif", "Minhaj-us-siraj", "Amir Khusrau",
                4,
                "He is regarded as the \"father of qawwali\"",
                3);
        addQuestion(h65);

        Question h66 = new Question("Who was the last Mughal emperor?",
                "Shah Alam-II", "Akbar-II", "Bahadur Shah-II", "Ahmad Shah",
                3,
                "He was the second son of and became the successor to his father, Akbar II",
                3);
        addQuestion(h66);

        Question h67 = new Question("Who was the last Mughal emperor to sit on the peacock throne?",
                "Jahandar Shah", "Muhammad Shah", "Shah Alam-I", "Bahadur Shah Zafar",
                2,
                "Muhammad Shah is said to be the last Mughal Emperor who sat on the Peacock throne.",
                3);
        addQuestion(h67);

        Question h68 = new Question(" Moti Masjid in the Red Fort, Delhi was constructed by",
                "Sher Shah", "Shahjahan", "Aurangzeb", "Bahadur Shah Zafar",
                3,
                "the sixth Mughal emperor, who ruled over almost the entire Indian subcontinent for a period of 49 years",
                3);
        addQuestion(h68);

        Question h69 = new Question("Sakhi, Sabad and Ramaini were the notable work of?",
                "Tansen", "Rahim", "Kabir", "Tulsidas",
                3,
                "a 15th century poet whose verses are found in Sikhism's scripture Guru Granth Sahib.",
                3);
        addQuestion(h69);

        Question h70 = new Question(" Bhakta Tukaram was a contemporary of which Mughal ruler?",
                "Akbar", "Babur", "Shahjahan", "Jahangir",
                4,
                "the fourth Mughal Emperor, who ruled from 1605 until his death in 1627.",
                3);
        addQuestion(h70);

        Question h71 = new Question("The city of Dhillika (Delhi) was founded by the:",
                "Chauhans", "Tomars", "Pawars", "Pariharas",
                2,
                "Delhi's name is associated with a Tomar King named Anangpal",
                3);
        addQuestion(h71);

        Question h72 = new Question("Who among the following Mughal Emperors were half Rajput?",
                "Shah Jahan & Aurangzeb", " Akbar & Jahangir", "Shah Jahan & Jahangir", "Jahangir & Humayun",
                3,
                "Shah Jahan & ________",
                3);
        addQuestion(h72);

        Question h73 = new Question("Who among the following was the counter part of Tipu Sultan during the Treaty of Seringapatnam?",
                "Warren Hastings", "Robert Clive", "Cornwallis", "Dalhousie",
                3,
                "The Treaty of Seringapatam was signed on 18 March 1792.Its signatories included Lord Cornwallis on behalf of the British East India.",
                3);
        addQuestion(h73);

        Question h74 = new Question("Which of the following Governor-General introduced the services of Railway and telegraph systems?",
                "Lord Cornwallis", "Lord Dalhousie", "Lord Wellesley", " Lord Bentinck",
                2,
                "Indian Railways and telegraph services were started during the reign of : Lord Cornwallis.",
                3);
        addQuestion(h74);

        Question h75 = new Question("Who among the following Indian cracks the British Indian Civil Services Examination in thr first time of Indian Histroy?",
                "Satyendranath Tagore", "R.C. Dutt", "Behari Lal Gupta", "Surendranath Banerjee",
                1,
                "Satyendranath Tagore became first Indian who cracked I.C.S. or Indian Civil Service examination.",
                3);
        addQuestion(h75);

        Question h76 = new Question("Who among the British Governor- General shown great interest in the preservation of ancient monuments?",
                "Lord Curzon", "Lord Ripon", "Lord Lytton", "Lorn Irwin",
                4,
                "The British Governor- General Lord Curzon shown great interest in the preservation of ancient monuments.",
                3);
        addQuestion(h76);

        Question h77 = new Question("Who was the Viceroy when the Jallianwala Bagh Massacre took place?",
                "Hardinge-II", "Chelmsford", "Reading", "Irwin",
                2,
                "Lord Chelmsford was the Viceroy of India when Jallianwala Bagh Massacre took place on April 13, 1919.",
                3);
        addQuestion(h77);

        Question h78 = new Question(" In the war of succession who had supported Aurangzeb?",
                "Jahanara", "Zebunnisha", "Jintunisha", "Roshanara",
                4,
                "a Mughal princess and the second daughter of Emperor Shah Jahan",
                3);
        addQuestion(h78);

        Question h79 = new Question("Who was the disciple of Vallabhacharya?",
                "Tulsidas", "Surdas", "Tukaram", "Ramanuja",
                2,
                "a 16th-century blind Hindu devotional poet and singer",
                3);
        addQuestion(h79);

        Question h80 = new Question("Who founded the faith on a ritual-free, simple “Sat Shri Akal” or the worship of God and truth?",
                "Dadu Dayal", "Guru Nanak Dev Ji", "Sri Chaitanya", "Guru Govind Singh Ji",
                2,
                "the founder of Sikhism and the first of the ten Sikh Gurus.",
                3);
        addQuestion(h80);

        Question h81 = new Question(" Which of the following text introduces bhakti marga (the path of faith/devotion) as one of three ways to spiritual freedom and release?",
                "Vedas", "Vedanta", "Brahamans", "The Bhagavad Gita",
                4,
                "a 700-verse Hindu scripture that is part of the epic Mahabharata.",
                3);
        addQuestion(h81);

        Question h82 = new Question("Which of the following Mughal King reign during the large scale famine in Gujarat and Deccan?",
                "Aurangzeb", "Jahangir", "Akbar", "Shahjahan",
                4,
                "His reign represented the height of the Mughal architecture, most notably the Taj Mahal",
                3);
        addQuestion(h82);

        Question h83 = new Question("Which Sikh guru was executed by Aurangzeb?",
                "Guru Tegh Bahadur Ji", "Guru Arjan Dev ji", "Guru Hargobind Singh ji", "Guru Gobind Singh ji",
                1,
                "the ninth of ten Gurus of the Sikh religion.",
                3);
        addQuestion(h83);

        Question h84 = new Question(" Which one of the following traders first came to India during the Mughal period?",
                "Portuguese", "Dutch", "Danish", "British",
                1,
                "Portuguese traders first came to India during the Mughal period.",
                3);
        addQuestion(h84);

        Question h85 = new Question("Who among the following was the real founder of the Aligarh Muslim University?",
                "Nawab Salimullah", "Sir Syed Ahmad Khan", "Abul Kalam Azad", "Muhammad Ali Jinnah",
                2,
                "Sir Syed Ahmad Khan",
                3);
        addQuestion(h85);

        Question h86 = new Question("Where did Gandhiji form the Satyagarh Sabha?",
                "Bombay", "Calcutta", "Poona", "Nagpur",
                1,
                "Anti-Rowlatt Satyagraha movement was started by Gandhi Ji at Bombay",
                3);
        addQuestion(h86);

        Question h87 = new Question("Which of the following incident ended the historic fast of Gandhi?",
                "Poona Pact", "Issue of White Paper", "Gandhi-Irwin Pact", "Arrival of Simon Commission",
                1,
                "Poona Pact was an agreement between Dr. Babasaheb Ambedkar and Mahatma Gandhi signed on September 24, 1932, at Yerwada Central Jail in Pune, to break Mahatma Gandhi's fast unto death.",
                3);
        addQuestion(h87);

        Question h88 = new Question("Who led the Bardoli Satyagraha in 1928?",
                "Morarji Desai", "M.K. Gandhi", "Mahadev Desai", "Vallabhbhai Patel",
                4,
                "He is popularly known as Sardar Patel",
                3);
        addQuestion(h88);

        Question h89 = new Question("Which of the following is the Journal published in Britain by the Committee of the Indian National Congress?",
                "Calcutta Gazette", "India", "Bengal Tribune", "Calcutta Tribune",
                2,
                "India",
                3);
        addQuestion(h89);

        Question h90 = new Question("Who founded the organization “servants of Indian society”?",
                "Dr. B.R. Ambedkar", "Mahatma Gandhi", "Dayanand Saraswati", "Gopal Krishna Gokhle",
                4,
                "The Servants of India Society was formed in pune on June 12, 1905 by Gopal Krishna Gokhale",
                3);
        addQuestion(h90);

        Question h91 = new Question("Who had written the book “poverty and un-British rule in India”?",
                "R.C. Dutta", "Dada bhai Naoroji", "S.C. Bose", "R.C. Das",
                2,
                "Dadabhai Naoroji",
                3);
        addQuestion(h91);

        Question h92 = new Question("Who said that Indian National Congress is a ‘begging institute’?",
                "Mahatma Gandhi", "Bipin Chandra Pal", "Bal Gangadhar Tilak", "Aurobindo Ghosh",
                4,
                "an Indian philosopher, yogi, guru, poet, and nationalist.",
                3);
        addQuestion(h92);

        Question h93 = new Question("Which of the following leader was not moderate?",
                "Dada Bhai Naoroji", "Anand Charlu", "Bipin Chandra Pal", "Madan Mohan Malviya",
                3,
                "Bipin Chandra Pal",
                3);
        addQuestion(h93);

        Question h94 = new Question("Which of the following event compel M.K Gandhi to withdraw the Nation’s cooperation from the British Government?",
                "Jallianwala massacre", "Bhagat Singh hanging", "Lathi Charge", "All of the above",
                1,
                "Jallianwala massacre",
                3);
        addQuestion(h94);

        Question h95 = new Question("When was the 'Quit India Movement' Launched by Mohandas Karamchand Gandhi?",
                "1946", "1939", "1942", "1940",
                3,
                "On 8 August 1942 at the All-India Congress Committee session in Bombay, Mohandas Karamchand Gandhi launched the 'Quit India' movement.",
                3);
        addQuestion(h95);

        Question h96 = new Question("Which of the following is not match correctly?",
                "Harappa-Pakistan", "Lothal-India", "Dholavira-Rajasthan", "Banwali-Haryana",
                3,
                "Dholavira is an archaeological site in the state of Gujrat",
                3);
        addQuestion(h96);

        Question h97 = new Question("Who is called as “beacon of light of Asia”",
                "Gautam Buddha", "Mahatma Gandhi", "Subhash Chandra Bose", "Abdul Gaffer Khan",
                3,
                "Netaji Subhash Chandra Bose is known as beacon of light of Asia.",
                3);
        addQuestion(h97);

        Question h98 = new Question("Who among the following  Mughal Emperor became the first pensioner of the  East India Company?",
                "Ahmed Shah", "Shah Alam II", "Bahadur Shah", "Muhammad Shah",
                2,
                "The first pensioner of East India company was Shah Alam II",
                3);
        addQuestion(h98);

        Question h99 = new Question("First Anglo Sikh War fought between",
                "The English & French", "The English & Portuguese", "The English & king Dalip Singh", "None of the above",
                3,
                "Between the Sikh Empire and East India Company",
                3);
        addQuestion(h99);

        Question h100 = new Question("Who among the following British Officials suppressed the Revolt of Jhansi?",
                "Colin Campbell", "Henry Havelock", "Henry Lawrence", "Hugh Rose",
                3,
                "Hugh Rose recaptured a number of provinces including Gwalior and was responsible for suppressing the revolt at Jhansi.",
                3);
        addQuestion(h100);


//geography
        Question g1 = new Question("Which of the following has moon as its satellite ?", "Mercury", "Earth", "Venus", "Mars", 2, "It is the third planet from the Sun", Category.GEOGRAPHY);
        addQuestion(g1);

//world polity
        Question wp1 = new Question("World Polity Question : A", "A", "B", "C", "D", 1, "Apple", Category.WORLD_POLITY);
        addQuestion(wp1);

//indian polity
        Question ip1 = new Question("Indian Polity Question : B", "A", "B", "C", "D", 2, "Apple", Category.INDIAN_POLITY);
        addQuestion(ip1);

//science
        Question sc1 = new Question("Science Question : C", "A", "B", "C", "D", 3, "Apple", Category.SCIENCE);
        addQuestion(s1);

//math
        Question m1 = new Question("Maths : A", "A", "B", "C", "D", 1, "Apple", Category.MATH);
        addQuestion(m1);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_HINT, question.getHint());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setHint(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_HINT)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs, null, null, null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setHint(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_HINT)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


}
