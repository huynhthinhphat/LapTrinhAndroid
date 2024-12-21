package com.example.gplxhanga;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FifthActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView TVCapPhep;
    private TextView TVNongDoCon;
    private TextView TVKhoangCach;
    private TextView TVHoiVeTuoi;
    private TextView TVDuong;
    private TextView TVGiaoNhau;
    private TextView TVNienHan;
    private TextView TVBienBaoCam;
    private TextView TVNhatChom;
    private TextView TVThuTu;
    private TextView TVHang;
    private TextView TVPhanNhom;
    private TextView TVTocDoTrong;
    private TextView TVTocDoNgoai_1;
    private TextView TVTocDoNgoai_2;
    private TextView TVTangGiam;
    private TextView TVPhuongTien;
    private TextView TVXeMay;
    private TextView TVNghiepVu;
    private TextView TVKyThuat;
    private TextView TVCauTao;
    private TextView TVQuyTac;

    private TextView TVCatogeryTocDoNgoai_1;
    private TextView TVCatogeryTocDoNgoai_2;

    private ImageButton btnBack;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        init();

        setTVCapPhep();
        setTVNongDoCon();
        setTVKhoangCach();
        setTVHoiVeTuoi();
        setTVDuong();
        setTVGiaoNhau();
        setTVNienHan();
        setTVBienBaoCam();
        setTVNhatChom();
        setTVThuTu();
        setTVHang();
        setTVPhanNhom();
        setTVTocDoTrong();
        setTVTocDoNgoai();
        setTVTangGiam();
        setTVPhuongTien();
        setTVXeMay();
        setTVNghiepVu();
        setTVKyThuat();
        setTVCauTao();
        setTVQuyTac();

        setUpBtnBack();
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarfifth);
        setSupportActionBar(toolbar);

        TVCapPhep = findViewById(R.id.cap_phep);
        TVNongDoCon = findViewById(R.id.nong_do_con);
        TVKhoangCach = findViewById(R.id.khoang_cach);
        TVHoiVeTuoi = findViewById(R.id.hoi_ve_tuoi);
        TVDuong = findViewById(R.id.tren_duong_cao_toc);
        TVGiaoNhau = findViewById(R.id.giaonhau);
        TVNienHan = findViewById(R.id.nien_han);
        TVBienBaoCam = findViewById(R.id.bien_bao_cam);
        TVNhatChom = findViewById(R.id.nhat_chom);
        TVThuTu = findViewById(R.id.thu_tu_uu_tien);
        TVHang = findViewById(R.id.hang_gplx);
        TVPhanNhom = findViewById(R.id.phan_nhom);
        TVTocDoTrong = findViewById(R.id.toc_do_toi_da_trong);
        TVTangGiam = findViewById(R.id.tang_giam_so);
        TVPhuongTien = findViewById(R.id.phuong_tien);
        TVXeMay = findViewById(R.id.xe_may_chuyen_dung);
        TVNghiepVu = findViewById(R.id.nghiep_vu);
        TVKyThuat = findViewById(R.id.ky_thuat);
        TVCauTao = findViewById(R.id.ct_sc);
        TVQuyTac = findViewById(R.id.quy_tac);

        TVTocDoNgoai_1 = findViewById(R.id.toc_do_toi_da_ngoai_1);
        TVTocDoNgoai_2 = findViewById(R.id.toc_do_toi_da_ngoai_2);
        TVCatogeryTocDoNgoai_1 = findViewById(R.id.catogery_toc_do_ngoai_1);
        TVCatogeryTocDoNgoai_2 = findViewById(R.id.catogery_toc_do_ngoai_2);

        btnBack = findViewById(R.id.btn_back);
    }

    private void setUpBtnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

    private SpannableStringBuilder setUpText(String content){

        // Tạo SpannableStringBuilder
        // SpannableStringBuilder cho phép áp dụng các định dạng đặc biệt (như thêm ký hiệu •, đổi màu, thay đổi kích thước, v.v.) lên từng phần cụ thể trong chuỗi.
        SpannableStringBuilder spannable = new SpannableStringBuilder(content);

        String[] lines = content.split("\n");

        int start = 0;
        for (String line : lines) {
            spannable.setSpan(new BulletSpan(20, Color.WHITE), start, start + line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Quy định phạm vi áp dụng (hiệu ứng không áp dụng vượt quá đoạn được chỉ định)
            start += line.length() + 1;
        }
        return spannable;
    }

    private void setTVCapPhep(){
        String content = "Đường cấm dừng, cấm đỗ, cấm đi do UBND cấp tỉnh cấp\n" +
                         "Xe quá khổ, quá tải do: cơ quan quản lý đường bộ có thẩm quyền cấp phép";

        TVCapPhep.setText(setUpText(content));
        TVCapPhep.setLineSpacing(10, 1.2f);
    }

    public void setTVNongDoCon() {
        String content = "Người điều khiển xe mô tô, ô tô, máy kéo trên đường mà trong máu hoặc hơi thở có nồng độ cồn: Bị nghiêm cấm";

        TVNongDoCon.setText(setUpText(content));
        TVNongDoCon.setLineSpacing(10, 1.2f);
    }

    public void setTVKhoangCach() {
        String content = "35m nếu vận tốc lưu hành(v) = 60 (km/h)\n" +
                "55m nếu 60 < v ≤ 80\n" +
                "70m nếu 80 < v ≤ 100\n" +
                "100m nếu 100 < v ≤ 120\n" +
                "Dưới 60km/h: Chủ động và đảm bảo khoảng cách.";

        TVKhoangCach.setText(setUpText(content));
        TVKhoangCach.setLineSpacing(10, 1.2f);
    }

    public void setTVHoiVeTuoi() {
        String content = "Tuổi tối đa hạng E: nam 55, nữ 50\n" +
                "Tuổi lấy bằng lái xe (cách nhau 3 tuổi)\n" +
                "Gắn máy: 16T (dưới 50cm3)\n" +
                "Mô tô + B1 + B2: 18T\n" +
                "C, FB: 21T\n" +
                "D, FC: 24T\n" +
                "E, FD: 27T";

        TVHoiVeTuoi.setText(setUpText(content));
        TVHoiVeTuoi.setLineSpacing(10, 1.2f);
    }

    public void setTVDuong() {
        String content = "Không được quay đầu xe, không lùi, không vượt\n" +
                "Không được vượt trên cầu hẹp có một làn xe.\n" +
                "Không được phép quay đầu xe ở phần đường dành cho người đi bộ qua đường.\n" +
                "Cấm lùi xe ở khu vực cấm dừng và nơi đường bộ giao nhau.\n" +
                "Tại nơi giao nhau không có tín hiệu đèn\n" +
                "Có vòng xuyến: Nhường đường bên trái\n" +
                "Không có vòng xuyến nhường bên phải";

        TVDuong.setText(setUpText(content));
        TVDuong.setLineSpacing(10, 1.2f);
    }

    public void setTVGiaoNhau() {
        String content = "Có vòng xuyến: Nhường đường bên trái\n" +
                "Không có vòng xuyến nhường bên phải";

        TVGiaoNhau.setText(setUpText(content));
        TVGiaoNhau.setLineSpacing(10, 1.2f);
    }

    public void setTVNienHan() {
        String content = "25 năm: ô tô tải\n" +
                "20 năm: ô tô chở người trên 9 chỗ";

        TVNienHan.setText(setUpText(content));
        TVNienHan.setLineSpacing(10, 1.2f);
    }

    public void setTVBienBaoCam() {
        String content = "Cấm ô tô (Gồm: mô tô 3 bánh, Xe Lam, xe khách) > Cấm xe tải > Cấm Máy kéo > Cấm rơ moóc, sơ mi rơ moóc";

        TVBienBaoCam.setText(setUpText(content));
        TVBienBaoCam.setLineSpacing(10, 1.2f);
    }
    public void setTVNhatChom() {
        String content = "Nhất chớm: Xe nào chớm tới vạch trước thì được đi trước.\n" +
                "Nhì ưu: Xe ưu tiên được đi trước. Thứ tự xe ưu tiên: Hỏa-Sự-An-Thương (Cứu hỏa - Quân sự - Công an - Cứu thương - Hộ đê - Đoàn xe tang).\n" +
                "Tam đường: Xe ở đường chính, đường ưu tiên.\n" +
                "Tứ hướng: Thứ tự hướng: Bên phải trống - Rẽ phải - Đi thẳng - Rẽ trái.";

        TVNhatChom.setText(setUpText(content));
        TVNhatChom.setLineSpacing(10, 1.2f);
    }

    public void setTVThuTu() {
        String content = "Hỏa: Xe Cứu hỏa\n" +
                "Sự : Xe Quân sự\n" +
                "An: Xe Công an\n" +
                "Thương: Xe cứu thương\n" +
                "Xe hộ đê, xe đi làm nhiệm vụ khẩn cấp\n" +
                "Đoàn xe tang";

        TVThuTu.setText(setUpText(content));
        TVThuTu.setLineSpacing(10, 1.2f);
    }

    public void setTVHang() {
        String content = "A1 mô tô dưới 175 cm3 và xe 3 bánh của người khuyết tật\n" +
                "A2 mô tô 175 cm3 trở lên\n" +
                "A3 xe mô tô 3 bánh\n" +
                "B1 không hành nghề lái xe\n" +
                "B1, B2 đến 9 chỗ ngồi, xe tải dưới 3.500kg\n" +
                "C đến 9 chỗ ngồi, xe trên 3.500kg\n" +
                "D chở đến 30 người\n" +
                "E chở trên 30 người.\n" +
                "FC: C + kéo (ô tô đầu kéo, kéo sơ mi rơ moóc)\n" +
                "FE: E + kéo (ô tô chở khách nối toa)";

        TVHang.setText(setUpText(content));
        TVHang.setLineSpacing(10, 1.2f);
    }

    public void setTVPhanNhom() {
        String content = "Biển nguy hiểm (hình tam giác vàng)\n" +
                "Biển cấm (vòng tròn đỏ)\n" +
                "Biển hiệu lệnh (vòng tròn xanh)\n" +
                "Biển chỉ dẫn (vuông hoặc hình chữ nhật xanh)" +
                "Biển phụ (vuông, chữ nhật trắng đen): Hiệu lực nằm ở biển phụ khi có đặt biển phụ";

        TVPhanNhom.setText(setUpText(content));
        TVPhanNhom.setLineSpacing(10, 1.2f);
    }

    public void setTVTocDoTrong() {
        String content = "60km/h: Đối với đường đôi hoặc đường 1 chiều có từ 2 làn xe cơ giới trở lên\n" +
                "50km/h: Đối với đường 2 chiều hoặc đường 1 chiều có 1 làn xe cơ giới";

        TVTocDoTrong.setText(setUpText(content));
        TVTocDoTrong.setLineSpacing(10, 1.2f);
    }
    public void setTVTocDoNgoai() {
        TVCatogeryTocDoNgoai_1.setText("1. Đối với đường đôi hoặc đường 1 chiều có từ 2 làn xe cơ giới trở lên");
        String content_1 = "80km/h: Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ xe buýt), ô tô tải có trọng tải ≤3.5 tấn.\n" +
                "70km/h: Xe ô tô chở người trên 30 chỗ (trừ xe buýt), ô tô tải có trọng tải >3.5 tấn (trừ ô tô xitec).\n" +
                "60km/h: Ô tô buýt, ô tô đầu kéo kéo sơ mi rơ mooc, xe mô tô, ô tô chuyên dùng (trừ ô tô trộn vữa, trộn bê tông).\n" +
                "50km/h: Ô tô kéo rơ mooc, ô tô kéo xe khác, ô tô trộn vữa, ô tô trộn bê tông, ô tô xi téc.";
        TVTocDoNgoai_1.setText(setUpText(content_1));
        TVTocDoNgoai_1.setLineSpacing(10, 1.2f);

        TVCatogeryTocDoNgoai_2.setText("2. Đối với đường 2 chiều hoặc đường 1 chiều có 1 làn xe cơ giới");
        String content_2 = "90km/h: Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ xe buýt), ô tô tải có trọng tải ≤3.5 tấn.\n" +
                "80km/h: Xe ô tô chở người trên 30 chỗ (trừ xe buýt), ô tô tải có trọng tải >3.5 tấn (trừ ô tô xitec).\n" +
                "70km/h: Ô tô buýt, ô tô đầu kéo kéo sơ mi rơ mooc, xe mô tô, ô tô chuyên dùng (trừ ô tô trộn vữa, trộn bê tông).\n" +
                "60km/h: Ô tô kéo rơ mooc, ô tô kéo xe khác, ô tô trộn vữa, ô tô trộn bê tông, ô tô xi téc.\n";

        TVTocDoNgoai_2.setText(setUpText(content_2));
        TVTocDoNgoai_2.setLineSpacing(10, 1.2f);
    }

    public void setTVTangGiam() {
        String content = "Tăng 1 Giảm 2 (giảm số chọn ý có từ “vù ga”)";

        TVTangGiam.setText(setUpText(content));
        TVTangGiam.setLineSpacing(10, 1.2f);
    }

    public void setTVPhuongTien() {
        String content = "Bao gồm phương tiện giao thông cơ giới đường bộ và phương tiện giao thông thô sơ đường bộ\n" +
                "Phương tiện tham gia giao thông đường bộ\n" +
                "Gồm phương tiện giao thông đường bộ và xe máy chuyên dùng";

        TVPhuongTien.setText(setUpText(content));
        TVPhuongTien.setLineSpacing(10, 1.2f);
    }

    public void setTVXeMay() {
        String content = "Gồm xe máy thi công, xe máy nông nghiệp, lâm nghiệp và các loại xe đặc chủng khác sử dụng và mục đích quốc phòng, an ninh có tham gia giao thông đường bộ";

        TVXeMay.setText(setUpText(content));
        TVXeMay.setLineSpacing(10, 1.2f);
    }

    public void setTVNghiepVu() {
        String content = "Không lái xe liên tục quá 4 giờ.\n" +
                "Không làm việc 1 ngày của lái xe quá 10 giờ.\n" +
                "Người kinh doanh vận tải không được tự ý thay đổi vị trí đón trả khách.\n" +
                "Vận chuyển hàng nguy hiểm phải có giấy phép.";

        TVNghiepVu.setText(setUpText(content));
        TVNghiepVu.setLineSpacing(10, 1.2f);
    }

    public void setTVKyThuat() {
        String content = "Xe mô tô xuống dốc dài cần sử dụng cả phanh trước và phanh sau để giảm tốc độ.\n" +
                "Khởi hành xe ô tô số tự động cần đạp phanh chân hết hành trình.\n" +
                "Thực hiện phanh tay cần phải bóp khóa hãm đẩy cần phanh tay về phía trước.\n" +
                "Khởi hành ô tô sử dụng hộp số đạp côn hết hành trình.\n" +
                "Thực hiện quay đầu xe với tốc độ thấp.\n" +
                "Lái xe ô tô qua đường sắt không rào chắn thì cách 5 mét hạ kính cửa, tắt âm thanh, quan sát.\n" +
                "Mở cửa xe thì quan sát rồi mới mở hé cánh cửa.";

        TVKyThuat.setText(setUpText(content));
        TVKyThuat.setLineSpacing(10, 1.2f);
    }

    public void setTVCauTao() {
        String content = "Yêu cầu của kính chắn gió, chọn “Loại kính an toàn“.\n" +
                "Âm lượng của còi là từ 90dB đến 115 dB.\n" +
                "Động cơ diesel không nổ do nhiên liệu lẫn tạp chất.\n" +
                "Dây đai an toàn có cơ cấu hãm giữ chặt dây khi giật dây đột ngột.\n" +
                "Động cơ 4 kỳ thì pít tông thực hiện 4 hành trình.\n" +
                "Hệ thống bôi trơn giảm ma sát.\n" +
                "Niên hạn ô tô trên 9 chỗ ngồi là 20 năm.\n" +
                "Niên hạn ô tô tải là 25 năm.\n" +
                "Động cơ ô tô biến nhiệt năng thành cơ năng.\n" +
                "Hệ thống truyền lực truyền mô men quay từ động cơ tới bánh xe.\n" +
                "Ly hợp (côn) truyền hoặc ngắt truyền động từ động cơ đến hộp số.\n" +
                "Hộp số ô tô đảm bảo chuyển động lùi.\n" +
                "Hệ thống lái dùng để thay đổi hướng.\n" +
                "Hệ thống phanh giúp giảm tốc độ.\n" +
                "Ắc quy để tích trữ điện năng.\n" +
                "Khởi động xe tự động phải đạp phanh.";

        TVCauTao.setText(setUpText(content));
        TVCauTao.setLineSpacing(10, 1.2f);
    }

    public void setTVQuyTac() {
        String content = "Thứ tự ưu tiên không vòng xuyến: Xe vào ngã ba, ngã tư trước - Xe ưu tiên - Đường ưu tiên - Đường cùng cấp theo thứ tự bên phải trống - rẽ phải - đi thẳng - rẽ trái.\n" +
                "Giao nhau cùng cấp có vòng xuyến: Chưa vào vòng xuyến thì ưu tiên xe bên phải; đã vào vòng xuyến ưu tiên xe từ bên trái tới.\n" +
                "Xe xuống dốc phải nhường đường cho xe đang lên dốc";

        TVQuyTac.setText(setUpText(content));
        TVQuyTac.setLineSpacing(10, 1.2f);
    }
}
