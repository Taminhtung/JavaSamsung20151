import java.util.ArrayList;
import java.util.Scanner;

public class LopSinhVien {

	public static void main(String[] args) {

		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Nhap vao so sinh vien");
		int soSV = scanner.nextInt();

		for (int i = 0; i < soSV; i++) {
			System.out.println("Nhap vao ma so sinh vien");
			int ms = scanner.nextInt();
			System.out.println("Nhap vao ten sinh vien");
			String name = scanner2.nextLine();
			System.out.println("Nhap vao diem cua sinh vien");
			int diem = scanner.nextInt();

			list.add(new SinhVien(ms, name, diem));

		}
		System.out.println("Da nhap xong");
		System.out.println("=======================================");
		System.out.println("Day la bang diem da nhap");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getMaSoSinhVien() + "	"
					+ list.get(i).getNameSinhVien() + "		"
					+ list.get(i).getDiemSinhVien());
		}

		System.out.println("=======================================");
		System.out.println("Nhap vao ten sinh vien ma ban muon tim kiem");
		String timKiemTen = scanner2.nextLine();
		int dem = 0;
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getNameSinhVien().equals(timKiemTen)) {
				System.out.println("Da tim thay sinh vien co ten la :"
						+ timKiemTen);
				System.out.println(list.get(i).getMaSoSinhVien() + "	"
						+ list.get(i).getNameSinhVien() + "		"
						+ list.get(i).getDiemSinhVien());
				dem++;
			}
		}
		if (dem == 0) {
			System.out.println("Khong co sinh vien nao co ten nhu vay");
		}
		System.out.println("=======================================");

		int diemTamThoi = 0;
		for (int i = 0; i < 11; i++) {
			int soDiemTamThoi = 0;
			int soDiemBangI = 0;

			for (int j = 0; j < list.size(); j++) {

				if (list.get(j).getDiemSinhVien() == i)
					soDiemBangI++;
			}
			if (soDiemBangI > soDiemTamThoi) {
				soDiemTamThoi = soDiemBangI;
				diemTamThoi = i;
			}
		}
		System.out.println("Diem co nhieu sinh vien dat duoc nhat la="
				+ diemTamThoi);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDiemSinhVien() == diemTamThoi) {
				System.out.println();
				System.out.println(list.get(i).getMaSoSinhVien() + "	"
						+ list.get(i).getNameSinhVien() + "		"
						+ list.get(i).getDiemSinhVien());
			}
		}

	}
}
