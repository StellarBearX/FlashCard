import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class FlashcardApp {
    private JFrame frame;
    private JLabel englishLabel, thaiLabel, pronunciationLabel;
    private JButton showAnswerButton, nextButton, previousButton, backButton;
    private JButton unit8Button, unit9Button;
    private int currentIndex = 0;
    private String[][] flashcards;

    // Unit 8: รวมคำศัพท์เกี่ยวกับผมและการออกกำลังกาย
    private String[][] unit8 = {
            {"bunches", "ผมแกละ", "บัน-ชิซ"},
            {"buzz cut", "ทรงผมเกรียน", "บัซ-คัท"},
            {"fringe", "ผมหน้าม้า", "ฟรินจ์"},
            {"parting", "แสกผม", "พาร-ทิง"},
            {"ponytail", "ผมหางม้า", "โพ-นี-เทล"},
            {"plaits", "ผมเปีย", "เพลทส์"},
            {"bleach", "ฟอกสีผม", "บลีช"},
            {"curl", "ดัดลอนผม", "เคิร์ล"},
            {"dye", "ย้อมสีผม", "ได"},
            {"put up", "เกล้าผม/รวบผมขึ้น", "พัท อัพ"},
            {"straighten", "หนีบผมให้ตรง", "สเตรท-เท่น"},
            {"blow-dry", "การเป่าผมให้แห้งและจัดทรง", "โบว์-ได"},
            {"perm", "การดัดผม", "เพิร์ม"},
            {"shave", "การโกนหนวด/โกนผม", "เชฟ (หรือ เชย์ฟ)"},
            {"treatment", "การบำรุงผม/การทำทรีตเมนต์", "ทรีท-เม้นท์"},
            {"trim", "การเล็มผม", "ทริม"},
            {"highlights/lowlights", "ไฮไลต์/โลว์ไลต์ผม", "ไฮ-ไลท์ และ โลว์-ไลท์"},
            {"manicure", "การทำเล็บมือ", "แมนนิคิวร์"},
            {"pedicure", "การทำเล็บเท้า", "เพดิคิวร์"},
            {"facial", "การทำทรีตเมนต์ใบหน้า", "เฟ-เชียล"},
            {"massage", "การนวด", "มาซาจ"},
            {"waxing", "การแว็กซ์ขน", "แว็กซิง"},
            {"fake tan", "การทำผิวสีแทน", "เฟค แทน"},
            // คำศัพท์เพิ่มเติมเกี่ยวกับการออกกำลังกาย
            {"(use) an exercise bike", "ใช้จักรยานออกกำลังกาย", "เอ็ก-เซอร์-ไซส์ ไบค์"},
            {"(use) a running machine", "ใช้ลู่วิ่ง", "รัน-นิ่ง เมชีน"},
            {"(do/lift) weights", "ยกน้ำหนัก", "ลิฟท์ เวทส์"},
            {"(use) a rowing machine", "ใช้เครื่องพาย", "โรว์-อิ้ง เมชีน"},
            {"(use) a cross-trainer", "ใช้เครื่องครอสเทรนเนอร์", "ครอส-เทรน-เนอร์"},
            {"(use) a yoga mat", "ใช้เสื่อโยคะ", "โย-กะ แมท"},
            {"do sit-ups", "ทำซิทอัพ", "ซิท-อัพส์"},
            {"do press-ups", "ทำท่าดันพื้น", "เพรส-อัพส์"},
            {"do stretches", "ทำการยืดเหยียด", "สเตรช-เซส"},
            {"do aerobics", "ทำแอโรบิก", "แอ-โร-บิคส์"},
            {"do spinning", "ทำสปินนิ่ง", "สปิน-นิ่ง"},
            {"do Pilates", "ทำพิลาทิส", "พิลาทิส"}
    };

    // Unit 9: รวมคำศัพท์หมวด Wars and battles, historic buildings และ word building
    private String[][] unit9 = {
            // B. Wars and battles, historic buildings
            {"archer", "พลธนู/นักยิงธนู", "อาร์-เชอร์"},
            {"arrow", "ลูกธนู", "แอ-โรว์"},
            {"bow", "คันธนู", "โบว์"},
            {"helmet", "หมวกเหล็ก/หมวกกันน็อก", "เฮล-เมท"},
            {"shield", "โล่", "ชีลด์"},
            {"battle", "การสู้รบ/สมรภูมิ", "แบท-เทิ่ล"},
            {"fight", "ต่อสู้/การต่อสู้", "ไฟท์"},
            {"defeat", "เอาชนะ/ความพ่ายแพ้", "ดี-ฟีท"},
            {"succeed", "ประสบความสำเร็จ", "ซัก-ซีด"},
            {"invade", "บุกรุก/รุกราน", "อิน-เวด"},
            {"crown", "สวมมงกุฎ", "คราวน์"},
            {"retreat", "ถอยทัพ/การถอยทัพ", "รี-ทรีท"},
            {"destroy", "ทำลาย", "ดิส-ทรอย"},
            {"abbey", "อาราม/วัดในศาสนาคริสต์", "แอบ-บี้"},
            {"castle", "ปราสาท", "แคส-เทิล"},
            {"cathedral", "โบสถ์ขนาดใหญ่", "แค-ธี-ดรอล"},
            {"palace", "พระราชวัง", "พา-เลซ"},
            {"tower", "หอคอย", "เทา-เวอร์"},
            {"aisle", "ทางเดินในโบสถ์หรือระหว่างแถวที่นั่ง", "ไอล์"},
            {"column", "เสา", "คอลัมน์"},
            {"crypt", "ห้องใต้ดินสำหรับเก็บศพ", "คริปท์"},
            {"dome", "โดม/หลังคาทรงกลม", "โดม"},
            {"gallery", "ระเบียงหรือห้องแสดงภาพในโบสถ์", "แกล-เลอรี่"},
            {"nave", "โถงกลางในโบสถ์", "เนฟ"},
            {"tomb", "สุสาน/หลุมฝังศพ", "ทูม"},
            {"transept", "ปีกขวางของโบสถ์รูปกากบาท", "แทรน-เซปท์"},
            {"thatched roof", "หลังคามุงจาก", "แธทช์ รูฟ"},
            {"plaque", "แผ่นป้ายจารึก", "แพล็ค"},
            {"pit", "หลุม", "พิท"},
            {"candlelight", "แสงเทียน", "แคน-เดิ้ล ไลท์"},
            // Total recall & Here comes the bride (word building)
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
            {"unlikely to", "ไม่น่าจะเป็นไปได้", "ัน-ไลค-ลี่ ทู"},
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
            {"pleasant/pleased", "น่าพอใจ/รู้สึกพอใจ", "เพลส-เซนท์ / พลีสด"},
            {"unpleasant", "ไม่น่าพอใจ", "ัน-เพลส-เซนท์"},
            {"severe", "รุนแรง/เคร่งครัด", "ซี-วีร์"},
            {"severity", "ความรุนแรง", "ซี-วี-ริตี้"}
    };

    public FlashcardApp() {
        try {
            // บังคับใช้ CrossPlatformLookAndFeel เพื่อให้สีปุ่มเป็นไปตามที่กำหนด (โดยเฉพาะบน macOS)
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ขยายขนาดหน้าจอให้ใหญ่ขึ้น
        frame = new JFrame("Flashcard Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        showUnitSelection();
        frame.setVisible(true);
    }

    // หน้าจอเลือก Unit
    private void showUnitSelection() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender

        unit8Button = createCustomButton("Unit 8", new Color(25, 25, 112), Color.WHITE, 20);
        unit9Button = createCustomButton("Unit 9", new Color(34, 139, 34), Color.WHITE, 20);

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
        frame.revalidate();
        frame.repaint();
    }

    // เซสชันแฟลชการ์ด
    private void startFlashcardSession() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // Light Gray

        // พาเนลสำหรับแสดงคำศัพท์
        JPanel cardPanel = new JPanel(new GridLayout(3, 1));
        cardPanel.setBackground(new Color(255, 250, 240)); // Floral White

        englishLabel = new JLabel("", SwingConstants.CENTER);
        thaiLabel = new JLabel("", SwingConstants.CENTER);
        pronunciationLabel = new JLabel("", SwingConstants.CENTER);

        // ปรับขนาดตัวหนังสือให้ใหญ่ขึ้น
        englishLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        thaiLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        pronunciationLabel.setFont(new Font("SansSerif", Font.BOLD, 26));

        englishLabel.setForeground(new Color(25, 25, 112));  // Midnight Blue
        thaiLabel.setForeground(new Color(34, 139, 34));     // Forest Green
        pronunciationLabel.setForeground(new Color(128, 0, 128)); // Purple

        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);

        cardPanel.add(englishLabel);
        cardPanel.add(thaiLabel);
        cardPanel.add(pronunciationLabel);

        // พาเนลสำหรับปุ่มควบคุม, จัดให้อยู่ตรงกลาง
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // สร้างปุ่มตามลำดับที่ต้องการ พร้อมปรับขนาดฟอนต์ให้ใหญ่ขึ้น (18 pt)
        backButton = createCustomButton("Back to Unit Selection", new Color(178, 34, 34), Color.WHITE, 18);
        backButton.addActionListener(e -> showUnitSelection());

        showAnswerButton = createCustomButton("Show Answer", new Color(0, 128, 0), Color.WHITE, 18);
        showAnswerButton.addActionListener(e -> {
            thaiLabel.setVisible(true);
            pronunciationLabel.setVisible(true);
        });

        previousButton = createCustomButton("Previous", new Color(70, 130, 180), Color.WHITE, 18);
        previousButton.addActionListener(e -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = flashcards.length - 1;
            }
            displayCard();
        });

        nextButton = createCustomButton("Next", new Color(70, 130, 180), Color.WHITE, 18);
        nextButton.addActionListener(e -> {
            currentIndex++;
            if (currentIndex >= flashcards.length) {
                currentIndex = 0;
            }
            displayCard();
        });

        // เพิ่มปุ่มลงใน panel ตามลำดับที่ต้องการ
        buttonPanel.add(backButton);
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);

        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        displayCard();
        frame.revalidate();
        frame.repaint();
    }

    // ฟังก์ชันสร้างปุ่มแบบ custom ที่รับพารามิเตอร์เพิ่มเติมสำหรับขนาดฟอนต์
    private JButton createCustomButton(String text, Color bgColor, Color fgColor, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setUI(new BasicButtonUI());
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    // ฟังก์ชันแสดงแฟลชการ์ดที่ currentIndex
    private void displayCard() {
        englishLabel.setText("English: " + flashcards[currentIndex][0]);
        thaiLabel.setText("Thai: " + flashcards[currentIndex][1]);
        pronunciationLabel.setText("Pronunciation: " + flashcards[currentIndex][2]);
        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashcardApp());
    }
}
