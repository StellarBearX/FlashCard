import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class FlashcardApp {
    private JFrame frame;
    private JLabel englishLabel, posLabel, thaiLabel, pronunciationLabel;
    private JButton showAnswerButton, nextButton, previousButton, backButton;
    private JButton unit8Button, unit9Button;
    private int currentIndex = 0;
    private String[][] flashcards;
    private JLabel counterLabel;

    // Unit 8: รวมคำศัพท์เกี่ยวกับผมและการออกกำลังกาย
    // คอลัมน์: Word, Thai, Pronunciation, Part of Speech
    private String[][] unit8 = {
            {"bunches", "ผมแกละ", "บัน-ชิซ", "Noun"},
            {"buzz cut", "ทรงผมเกรียน", "บัซ-คัท", "Noun"},
            {"fringe", "ผมหน้าม้า", "ฟรินจ์", "Noun"},
            {"parting", "แสกผม", "พาร-ทิง", "Noun"},
            {"ponytail", "ผมหางม้า", "โพ-นี-เทล", "Noun"},
            {"plaits", "ผมเปีย", "เพลทส์", "Noun"},
            {"bleach", "ฟอกสีผม", "บลีช", "Verb"},
            {"curl", "ดัดลอนผม", "เคิร์ล", "Verb"},
            {"dye", "ย้อมสีผม", "ได", "Verb"},
            {"put up", "เกล้าผม/รวบผมขึ้น", "พัท อัพ", "Phrasal Verb"},
            {"straighten", "หนีบผมให้ตรง", "สเตรท-เท่น", "Verb"},
            {"blow-dry", "การเป่าผมให้แห้งและจัดทรง", "โบว์-ได", "Noun"},
            {"perm", "การดัดผม", "เพิร์ม", "Noun"},
            {"shave", "การโกนหนวด/โกนผม", "เชฟ (หรือ เชย์ฟ)", "Verb"},
            {"treatment", "การบำรุงผม/การทำทรีตเมนต์", "ทรีท-เม้นท์", "Noun"},
            {"trim", "การเล็มผม", "ทริม", "Verb"},
            {"highlights/lowlights", "ไฮไลต์/โลว์ไลต์ผม", "ไฮ-ไลท์ และ โลว์-ไลท์", "Noun"},
            {"manicure", "การทำเล็บมือ", "แมนนิคิวร์", "Noun"},
            {"pedicure", "การทำเล็บเท้า", "เพดิคิวร์", "Noun"},
            {"facial", "การทำทรีตเมนต์ใบหน้า", "เฟ-เชียล", "Noun"},
            {"massage", "การนวด", "มาซาจ", "Noun"},
            {"waxing", "การแว็กซ์ขน", "แว็กซิง", "Noun"},
            {"fake tan", "การทำผิวสีแทน", "เฟค แทน", "Noun"},
            // คำศัพท์เพิ่มเติมเกี่ยวกับการออกกำลังกาย
            {"(use) an exercise bike", "ใช้จักรยานออกกำลังกาย", "เอ็ก-เซอร์-ไซส์ ไบค์", "Noun"},
            {"(use) a running machine", "ใช้ลู่วิ่ง", "รัน-นิ่ง เมชีน", "Noun"},
            {"(do/lift) weights", "ยกน้ำหนัก", "ลิฟท์ เวทส์", "Noun"},
            {"(use) a rowing machine", "ใช้เครื่องพาย", "โรว์-อิ้ง เมชีน", "Noun"},
            {"(use) a cross-trainer", "ใช้เครื่องครอสเทรนเนอร์", "ครอส-เทรน-เนอร์", "Noun"},
            {"(use) a yoga mat", "ใช้เสื่อโยคะ", "โย-กะ แมท", "Noun"},
            {"do sit-ups", "ทำซิทอัพ", "ซิท-อัพส์", "Verb"},
            {"do press-ups", "ทำท่าดันพื้น", "เพรส-อัพส์", "Verb"},
            {"do stretches", "ทำการยืดเหยียด", "สเตรช-เซส", "Verb"},
            {"do aerobics", "ทำแอโรบิก", "แอ-โร-บิคส์", "Verb"},
            {"do spinning", "ทำสปินนิ่ง", "สปิน-นิ่ง", "Verb"},
            {"do Pilates", "ทำพิลาทิส", "พิลาทิส", "Verb"}
    };

    // Unit 9: รวมคำศัพท์หมวด Wars and battles, historic buildings และ word building
    private String[][] unit9 = {
            // Wars and battles, historic buildings
            {"archer", "พลธนู/นักยิงธนู", "อาร์-เชอร์", "Noun"},
            {"arrow", "ลูกธนู", "แอ-โรว์", "Noun"},
            {"bow", "คันธนู", "โบว์", "Noun"},
            {"helmet", "หมวกเหล็ก/หมวกกันน็อก", "เฮล-เมท", "Noun"},
            {"shield", "โล่", "ชีลด์", "Noun"},
            {"battle", "การสู้รบ/สมรภูมิ", "แบท-เทิ่ล", "Noun"},
            {"fight", "ต่อสู้/การต่อสู้", "ไฟท์", "Noun"},
            {"defeat", "เอาชนะ/ความพ่ายแพ้", "ดี-ฟีท", "Noun"},
            {"succeed", "ประสบความสำเร็จ", "ซัก-ซีด", "Verb"},
            {"invade", "บุกรุก/รุกราน", "อิน-เวด", "Verb"},
            {"crown", "สวมมงกุฎ", "คราวน์", "Verb"},
            {"retreat", "ถอยทัพ/การถอยทัพ", "รี-ทรีท", "Noun"},
            {"destroy", "ทำลาย", "ดิส-ทรอย", "Verb"},
            {"abbey", "อาราม/วัดในศาสนาคริสต์", "แอบ-บี้", "Noun"},
            {"castle", "ปราสาท", "แคส-เทิล", "Noun"},
            {"cathedral", "โบสถ์ขนาดใหญ่", "แค-ธี-ดรอล", "Noun"},
            {"palace", "พระราชวัง", "พา-เลซ", "Noun"},
            {"tower", "หอคอย", "เทา-เวอร์", "Noun"},
            {"aisle", "ทางเดินในโบสถ์หรือระหว่างแถวที่นั่ง", "ไอล์", "Noun"},
            {"column", "เสา", "คอลัมน์", "Noun"},
            {"crypt", "ห้องใต้ดินสำหรับเก็บศพ", "คริปท์", "Noun"},
            {"dome", "โดม/หลังคาทรงกลม", "โดม", "Noun"},
            {"gallery", "ระเบียงหรือห้องแสดงภาพในโบสถ์", "แกล-เลอรี่", "Noun"},
            {"nave", "โถงกลางในโบสถ์", "เนฟ", "Noun"},
            {"tomb", "สุสาน/หลุมฝังศพ", "ทูม", "Noun"},
            {"transept", "ปีกขวางของโบสถ์รูปกากบาท", "แทรน-เซปท์", "Noun"},
            {"thatched roof", "หลังคามุงจาก", "แธทช์ รูฟ", "Noun"},
            {"plaque", "แผ่นป้ายจารึก", "แพล็ค", "Noun"},
            {"pit", "หลุม", "พิท", "Noun"},
            {"candlelight", "แสงเทียน", "แคน-เดิ้ล ไลท์", "Noun"},
            // Total recall & Here comes the bride (word building)
            {"remind", "เตือนความจำ", "รี-มายด์", "Verb"},
            {"memorize", "จำ", "เมมอ-ไรซ์", "Verb"},
            {"recall", "นึกออก/จำได้", "รี-คอว์ล", "Verb"},
            {"unforgettable", "ที่ลืมไม่ลง", "อัน-ฟอร์-เก็ต-เทเบิ้ล", "Adjective"},
            {"confidence", "ความมั่นใจ", "คอน-ฟิ-เดนซ์", "Noun"},
            {"confident", "มั่นใจ", "คอน-ฟิ-เดนท์", "Adjective"},
            {"confidently", "อย่างมั่นใจ", "คอน-ฟิ-เดนท์-ลี่", "Adverb"},
            {"emotion", "อารมณ์", "อี-โม-ชัน", "Noun"},
            {"emotional", "เกี่ยวกับอารมณ์/อ่อนไหว", "อี-โม-เชิน-นอล", "Adjective"},
            {"emotionally", "ในเชิงอารมณ์", "อี-โม-เชิน-นาล-ลี่", "Adverb"},
            {"importance", "ความสำคัญ", "อิม-พอร์-เท้นซ์", "Noun"},
            {"important", "ที่สำคัญ", "อิม-พอร์-เท้นท์", "Adjective"},
            {"importantly", "โดยสำคัญ", "อิม-พอร์-เท้นท์-ลี่", "Adverb"},
            {"accuracy", "ความแม่นยำ", "แอค-คิว-เรซี่", "Noun"},
            {"accurate", "ที่แม่นยำ", "แอค-คิว-เรท", "Adjective"},
            {"accurately", "อย่างแม่นยำ", "แอค-คิว-เรท-ลี่", "Adverb"},
            {"person", "บุคคล", "เพอร์-สัน", "Noun"},
            {"personal", "ที่เป็นส่วนตัว", "เพอร์-สัน-นอล", "Adjective"},
            {"personally", "โดยส่วนตัว", "เพอร์-สัน-นาล-ลี่", "Adverb"},
            {"memory", "ความทรงจำ", "เมม-โม-รี", "Noun"},
            {"memorable", "น่าจดจำ", "เมม-อเร-เบิ้ล", "Adjective"},
            {"memorably", "อย่างน่าจดจำ", "เมม-อเร-เบล-ลี่", "Adverb"},
            {"intelligence", "ความฉลาด", "อิน-เทล-ลิเจนซ์", "Noun"},
            {"intelligent", "ฉลาด", "อิน-เทล-ลิเจนท์", "Adjective"},
            {"description", "การบรรยาย/คำอธิบาย", "ดิ-สคริป-ชัน", "Noun"},
            {"descriptive", "ซึ่งเป็นการบรรยาย", "ดิ-สคริป-ทีฟ", "Adjective"},
            {"occasion", "โอกาส/วาระ", "อค-เค-เชิน", "Noun"},
            {"occasional", "ซึ่งเป็นครั้งคราว", "อค-เค-เชิน-นอล", "Adjective"},
            {"occasionally", "โดยเป็นครั้งคราว", "อค-เค-เชิน-ลี่", "Adverb"},
            {"announcement", "การประกาศ", "อะ-นาวน์ซ์-เม้นท์", "Noun"},
            {"announce", "ประกาศ", "อะ-นาวน์ซ์", "Verb"},
            {"atom", "อะตอม", "แอะ-ทอม", "Noun"},
            {"atomic", "เกี่ยวกับอะตอม/เล็กมาก", "อะ-ทอม-มิก", "Adjective"},
            {"enter", "เข้าไป", "เอน-เทอร์", "Verb"},
            {"entrance", "ทางเข้า", "เอน-ทรานซ์", "Noun"},
            {"likely to", "มีแนวโน้มที่จะ", "ไลค-ลี่ ทู", "Adjective"},
            {"unlikely to", "ไม่น่าจะเป็นไปได้", "ัน-ไลค-ลี่ ทู", "Adjective"},
            {"secure", "ทำให้ปลอดภัย/ปลอดภัย", "ซี-คิวร์", "Verb"},
            {"security", "ความปลอดภัย", "ซี-คิว-ริตี้", "Noun"},
            {"connect", "เชื่อมต่อ", "คอน-เนคท์", "Verb"},
            {"connection", "การเชื่อมต่อ", "คอน-เนค-ชัน", "Noun"},
            {"autobiographic", "เกี่ยวกับอัตชีวประวัติ", "ออ-ทอ-ไบ-โอ-กราฟิก", "Adjective"},
            {"autobiography", "อัตชีวประวัติ", "ออ-ทอ-ไบ-โอ-กราฟ-ฟี", "Noun"},
            {"ease", "ความง่าย/ทำให้สบาย", "อีส", "Noun"},
            {"easy", "ง่าย/สะดวก", "อี-ซี่", "Adjective"},
            {"easily", "อย่างง่ายดาย", "อี-ซี่-ลี่", "Adverb"},
            {"able to", "ซึ่งสามารถ", "เอ-เบิล ทู", "Modal"},
            {"ability", "ความสามารถ", "อะ-บิล-ิตี้", "Noun"},
            {"capable", "ซึ่งสามารถ", "เค-พา-เบิล", "Adjective"},
            {"capability", "ความสามารถ", "เค-พา-บิล-ิตี้", "Noun"},
            {"pleasant/pleased", "น่าพอใจ/รู้สึกพอใจ", "เพลส-เซนท์ / พลีสด", "Adjective"},
            {"unpleasant", "ไม่น่าพอใจ", "ัน-เพลส-เซนท์", "Adjective"},
            {"severe", "รุนแรง/เคร่งครัด", "ซี-วีร์", "Adjective"},
            {"severity", "ความรุนแรง", "ซี-วี-ริตี้", "Noun"}
    };

    public FlashcardApp() {
        try {
            // ใช้ CrossPlatformLookAndFeel เพื่อให้สีปุ่มแสดงผลตามที่กำหนด
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ขยายหน้าจอให้ใหญ่ขึ้น
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
            // สับลำดับคำศัพท์แบบสุ่ม (ถ้าต้องการ)
            Collections.shuffle(Arrays.asList(flashcards));
            currentIndex = 0;
            startFlashcardSession();
        });

        unit9Button.addActionListener(e -> {
            flashcards = unit9;
            // สับลำดับคำศัพท์แบบสุ่ม (ถ้าต้องการ)
            Collections.shuffle(Arrays.asList(flashcards));
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

        // พาเนลสำหรับแสดงคำศัพท์ (เพิ่ม posLabel สำหรับ Part of Speech)
        JPanel cardPanel = new JPanel(new GridLayout(4, 1));
        cardPanel.setBackground(new Color(255, 250, 240)); // Floral White

        englishLabel = new JLabel("", SwingConstants.CENTER);
        posLabel = new JLabel("", SwingConstants.CENTER);
        thaiLabel = new JLabel("", SwingConstants.CENTER);
        pronunciationLabel = new JLabel("", SwingConstants.CENTER);

        // ปรับขนาดตัวหนังสือให้ใหญ่ขึ้น
        englishLabel.setFont(new Font("TH Sarabun New", Font.BOLD, 40));
        posLabel.setFont(new Font("TH Sarabun New", Font.BOLD, 40));
        thaiLabel.setFont(new Font("TH Sarabun New", Font.BOLD, 40));
        pronunciationLabel.setFont(new Font("TH Sarabun New", Font.BOLD, 40));

        englishLabel.setForeground(new Color(25, 25, 112));     // Midnight Blue
        posLabel.setForeground(new Color(128, 0, 0));             // Dark Red
        thaiLabel.setForeground(new Color(34, 139, 34));          // Forest Green
        pronunciationLabel.setForeground(new Color(128, 0, 128)); // Purple

        // ซ่อน posLabel, thaiLabel และ pronunciationLabel เมื่อเริ่มต้น
        posLabel.setVisible(false);
        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);

        cardPanel.add(englishLabel);
        cardPanel.add(posLabel);
        cardPanel.add(thaiLabel);
        cardPanel.add(pronunciationLabel);

        // พาเนลสำหรับปุ่มควบคุม, จัดให้อยู่ตรงกลาง
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // สร้างปุ่มต่าง ๆ
        backButton = createCustomButton("Back to Unit Selection", new Color(178, 34, 34), Color.WHITE, 18);
        backButton.addActionListener(e -> showUnitSelection());

        showAnswerButton = createCustomButton("Show Answer", new Color(0, 128, 0), Color.WHITE, 18);
        showAnswerButton.addActionListener(e -> {
            posLabel.setVisible(true);
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

        // สร้างปุ่มสุ่ม (Random)
        JButton randomButton = createCustomButton("Random", new Color(218, 165, 32), Color.WHITE, 18);
        randomButton.addActionListener(e -> {
            currentIndex = new Random().nextInt(flashcards.length);
            displayCard();
        });

        // สร้าง counterLabel สำหรับแสดงเลขที่คำศัพท์
        counterLabel = new JLabel("", SwingConstants.CENTER);
        counterLabel.setFont(new Font("TH Sarabun New", Font.BOLD, 20));
        counterLabel.setForeground(new Color(0, 0, 0)); // Black

        // เพิ่มปุ่มลงใน panel
        buttonPanel.add(backButton);
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(randomButton);

        // ตั้งค่า Key Bindings สำหรับลูกศรซ้าย, ขวา และ Enter
        InputMap im = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "previousAction");
        am.put("previousAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousButton.doClick();
            }
        });
        im.put(KeyStroke.getKeyStroke("RIGHT"), "nextAction");
        am.put("nextAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButton.doClick();
            }
        });
        im.put(KeyStroke.getKeyStroke("ENTER"), "showAnswerAction");
        am.put("showAnswerAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnswerButton.doClick();
            }
        });

        frame.add(cardPanel, BorderLayout.CENTER);

        // Panel สำหรับ counterLabel
        JPanel counterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        counterPanel.setBackground(new Color(245, 245, 245));
        counterPanel.add(counterLabel);

        // เพิ่ม counterPanel กับ buttonPanel ลงใน frame
        frame.add(counterPanel, BorderLayout.NORTH);
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
        posLabel.setText("Part of Speech: " + flashcards[currentIndex][3]);
        thaiLabel.setText("Thai: " + flashcards[currentIndex][1]);
        pronunciationLabel.setText("Pronunciation: " + flashcards[currentIndex][2]);
        posLabel.setVisible(false);
        thaiLabel.setVisible(false);
        pronunciationLabel.setVisible(false);

        // อัปเดต counterLabel (เช่น "Card 1 of 34")
        counterLabel.setText("Card " + (currentIndex + 1) + " of " + flashcards.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashcardApp());
    }
}
