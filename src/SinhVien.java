public class SinhVien {

	private int maSoSinhVien;
	private String nameSinhVien;
	private int diemSinhVien;

	public SinhVien(int msSV, String tenSV, int diemSV) {

		this.maSoSinhVien=msSV;
		this.nameSinhVien=tenSV;
		this.diemSinhVien=diemSV;
	}

	public int getMaSoSinhVien() {
		return maSoSinhVien;
	}

	public void setMaSoSinhVien(int maSoSinhVien) {
		this.maSoSinhVien = maSoSinhVien;
	}

	public String getNameSinhVien() {
		return nameSinhVien;
	}

	public void setNameSinhVien(String nameSinhVien) {
		this.nameSinhVien = nameSinhVien;
	}

	public int getDiemSinhVien() {
		return diemSinhVien;
	}

	public void setDiemSinhVien(int diemSinhVien) throws Exception {

		if (diemSinhVien > 10 || (diemSinhVien < 0))
			throw new Exception("diem lon hon bang 0 va nho hon bang 10");
		else
			this.diemSinhVien = diemSinhVien;
	}

}
