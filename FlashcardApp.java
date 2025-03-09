import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardApp {
    private JFrame frame;
    private JLabel englishLabel, thaiLabel, pronunciationLabel;
    private JButton showAnswerButton, nextButton, unit8Button, unit9Button, changeUnitButton;
    private int currentIndex = 0;
    private String[][] flashcards;

    private String[][] unit8 = {
            {"bunches", "ผมแกละ", "บัน-ชิซ"},
            {"buzz cut", "ทรงผมเกรียน", "บัซ-คัท"},
            {"fringe", "ผมหน้าม้า", "ฟรินจ์"},
            {"parting", "แสกผม", "พาร์-ทิง"},
            {"ponytail", "ผมหางม้า", "โพ-นี-เทล"},
            {"plaits", "ผมเปีย", "เพลทส์"},
            {"bleach", "ฟอกสีผม", "บลีช"},
            {"curl", "ดัดลอนผม", "เคิร์ล"},
            {"dye", "ย้อมสีผม", "ได"},
            {"put up", "เกล้าผม/รวบผมขึ้น", "พัท อัพ"},
            {"straighten", "หนีบผมให้ตรง", "สเตรท-เท่น"},
            {"blow-dry", "การเป่าผมให้แห้งและจัดทรง", "โบว์-ได"},
            {"perm", "การดัดผม", "เพิร์ม"},
            {"shave", "การโกนหนวด/โกนผม", "เชฟ"},
            {"treatment", "การบำรุงผม/การทำทรีตเมนต์", "ทรีท-เม้นท์"},
            {"trim", "การเล็มผม", "ทริม"},
            {"highlights/lowlights", "ไฮไลต์/โลว์ไลต์ผม", "ไฮ-ไลท์ และ โลว์-ไลท์"},
            {"manicure", "การทำเล็บมือ", "แมนนิคิวร์"},
            {"pedicure", "การทำเล็บเท้า", "เพดิคิวร์"},
            {"facial", "การทำทรีตเมนต์ใบหน้า", "เฟ-เชียล"},
            {"massage", "การนวด", "มาซาจ"},
            {"waxing", "การแว็กซ์ขน", "แว็กซิง"},
            {"fake tan", "การทำผิวสีแทน", "เฟค แทน"}

    };



    private String[][] unit9 = {
            {"remind", "เตือนความจำ", "รี-มายด์"},
            {"memorize", "จำ", "เมมอ-ไรซ์"},
            {"recall", "นึกออก/จำได้", "รี-คอว์ล"},
            {"unforgettable", "ที่ลืมไม่ลง", "อัน-ฟอร์-เก็ต-เทเบิ้ล"},
            {"confidence", "ความมั่นใจ", "คอน-ฟิ-เดนซ์"},
            {"confident", "มั่นใจ", "คอน-ฟิ-เดนท์"},
            {"confidently", "อย่างมั่นใจ", "คอน-ฟิ-เดนท์-ลี่"},
            {"emotion", "อารมณ์", "อี-โม-ชัน"},
            {"emotional", "เกี่ยวกับอารมณ์/อ่อนไหว", "อี-โม-เชิน-นอล"},
            {"emotionally", "ในเชิงอารมณ์", "อี-โม-เชิน-นาล-ลี่"},
            {"importance", "ความสำคัญ", "อิม-พอร์-เท้นซ์"},
            {"important", "ที่สำคัญ", "อิม-พอร์-เท้นท์"},
            {"importantly", "โดยสำคัญ", "อิม-พอร์-เท้นท์-ลี่"},
            {"accuracy", "ความแม่นยำ", "แอค-คิว-เรซี่"},
            {"accurate", "ที่แม่นยำ", "แอค-คิว-เรท"},
            {"accurately", "อย่างแม่นยำ", "แอค-คิว-เรท-ลี่"},
            {"person", "บุคคล", "เพอร์-สัน"},
            {"personal", "ที่เป็นส่วนตัว", "เพอร์-สัน-นอล"},
            {"personally", "โดยส่วนตัว", "เพอร์-สัน-นาล-ลี่"},
            {"memory", "ความทรงจำ", "เมม-โม-รี"},
            {"memorable", "น่าจดจำ", "เมม-อเร-เบิ้ล"},
            {"memorably", "อย่างน่าจดจำ", "เมม-อเร-เบล-ลี่"},
            {"intelligence", "ความฉลาด", "อิน-เทล-ลิเจนซ์"},
            {"intelligent", "ฉลาด", "อิน-เทล-ลิเจนท์"},
            {"description", "การบรรยาย/คำอธิบาย", "ดิ-สคริป-ชัน"},
            {"descriptive", "ซึ่งเป็นการบรรยาย", "ดิ-สคริป-ทีฟ"},
            {"occasion", "โอกาส/วาระ", "อค-เค-เชิน"},
            {"occasional", "ซึ่งเป็นครั้งคราว", "อค-เค-เชิน-นอล"},
            {"occasionally", "โดยเป็นครั้งคราว", "อค-เค-เชิน-ลี่"},
            {"announcement", "การประกาศ", "อะ-นาวน์ซ์-เม้นท์"},
            {"announce", "ประกาศ", "อะ-นาวน์ซ์"},
            {"atom", "อะตอม", "แอะ-ทอม"},
            {"atomic", "เกี่ยวกับอะตอม/เล็กมาก", "อะ-ทอม-มิก"},
            {"enter", "เข้าไป", "เอน-เทอร์"},
            {"entrance", "ทางเข้า", "เอน-ทรานซ์"},
            {"likely to", "มีแนวโน้มที่จะ", "ไลค-ลี่ ทู"},
            {"unlikely to", "ไม่น่าจะเป็นไปได้", "อัน-ไลค-ลี่ ทู"},
            {"secure", "ทำให้ปลอดภัย/ปลอดภัย", "ซี-คิวร์"},
            {"security", "ความปลอดภัย", "ซี-คิว-ริตี้"},
            {"connect", "เชื่อมต่อ", "คอน-เนคท์"},
            {"connection", "การเชื่อมต่อ", "คอน-เนค-ชัน"},
            {"autobiographic", "เกี่ยวกับอัตชีวประวัติ", "ออ-ทอ-ไบ-โอ-กราฟิก"},
            {"autobiography", "อัตชีวประวัติ", "ออ-ทอ-ไบ-โอ-กราฟ-ฟี"},
            {"ease", "ความง่าย/ทำให้สบาย", "อีส"},
            {"easy", "ง่าย/สะดวก", "อี-ซี่"},
            {"easily", "อย่างง่ายดาย", "อี-ซี่-ลี่"},
            {"able to", "ซึ่งสามารถ", "เอ-เบิล ทู"},
            {"ability", "ความสามารถ", "อะ-บิล-ิตี้"},
            {"capable", "ซึ่งสามารถ", "เค-พา-เบิล"},
            {"capability", "ความสามารถ", "เค-พา-บิล-ิตี้"},
            {"pleasant", "น่าพอใจ", "เพลส-เซนท์"},
            {"pleased", "รู้สึกพอใจ", "พลีสด"},
            {"unpleasant", "ไม่น่าพอใจ", "อัน-เพลส-เซนท์"},
            {"severe", "รุนแรง/เคร่งครัด", "ซี-วีร์"},
            {"severity", "ความรุนแรง", "ซี-วี-ริตี้"}

    };



    public FlashcardApp() {
        frame = new JFrame("Flashcard Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        unit8Button = new JButton("Unit 8");
        unit9Button = new JButton("Unit 9");

        unit8Button.addActionListener(e -> {
            flashcards = unit8;
            currentIndex = 0;
            startFlashcardSession();
        });

        unit9Button.addActionListener(e -> {
            flashcards = unit9;
            currentIndex = 0;
            startFlashcardSession();
        });

        frame.add(unit8Button);
        frame.add(unit9Button);
        frame.setVisible(true);
    }

    private void startFlashcardSession() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(5, 1));

        Font thaiFont = new Font("Tahoma", Font.PLAIN, 18);

        englishLabel = new JLabel("", SwingConstants.CENTER);
        thaiLabel = new JLabel("", SwingConstants.CENTER);
        thaiLabel.setFont(thaiFont);
        pronunciationLabel = new JLabel("", SwingConstants.CENTER);
        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);

        showAnswerButton = new JButton("Show Answer");
        showAnswerButton.addActionListener(e -> {
            thaiLabel.setVisible(true);
            pronunciationLabel.setVisible(true);
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> showNextCard());

        changeUnitButton = new JButton("Change Unit");
        changeUnitButton.addActionListener(e -> returnToUnitSelection());

        frame.add(englishLabel);
        frame.add(thaiLabel);
        frame.add(pronunciationLabel);
        frame.add(showAnswerButton);
        frame.add(nextButton);
        frame.add(changeUnitButton);

        showNextCard();
        frame.revalidate();
        frame.repaint();
    }

    private void showNextCard() {
        if (currentIndex >= flashcards.length) {
            currentIndex = 0;
        }
        englishLabel.setText("English: " + flashcards[currentIndex][0]);
        thaiLabel.setText("Thai: " + flashcards[currentIndex][1]);
        pronunciationLabel.setText("Pronunciation: " + flashcards[currentIndex][2]);
        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);
        currentIndex++;
    }

    private void returnToUnitSelection() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));
        frame.add(unit8Button);
        frame.add(unit9Button);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new FlashcardApp();
    }
}
