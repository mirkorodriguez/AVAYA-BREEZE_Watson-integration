package com.parlana.core.model;

import java.util.List;

public class CentralMaster {
    private Long idCentralNumber;

    private String centralNumber;

    private String centralCode;
    
    private String centralNumberPstn;

    private String centralSmgrIp;

    private String centralLang;

    private String centralAccessPwd;

    List<Extension> extensionList;
    
    public Long getIdCentralNumber() {
        return idCentralNumber;
    }

    public void setIdCentralNumber(Long idCentralNumber) {
        this.idCentralNumber = idCentralNumber;
    }

    public String getCentralNumber() {
        return centralNumber;
    }

    public String getCentralCode() {
		return centralCode;
	}

	public void setCentralCode(String centralCode) {
		this.centralCode = centralCode;
	}

	public void setCentralNumber(String centralNumber) {
        this.centralNumber = centralNumber == null ? null : centralNumber.trim();
    }

    public String getCentralNumberPstn() {
        return centralNumberPstn;
    }

    public void setCentralNumberPstn(String centralNumberPstn) {
        this.centralNumberPstn = centralNumberPstn == null ? null : centralNumberPstn.trim();
    }

    public String getCentralSmgrIp() {
        return centralSmgrIp;
    }

    public void setCentralSmgrIp(String centralSmgrIp) {
        this.centralSmgrIp = centralSmgrIp == null ? null : centralSmgrIp.trim();
    }

    public String getCentralLang() {
        return centralLang;
    }

    public void setCentralLang(String centralLang) {
        this.centralLang = centralLang == null ? null : centralLang.trim();
    }

    public String getCentralAccessPwd() {
        return centralAccessPwd;
    }

    public void setCentralAccessPwd(String centralAccessPwd) {
        this.centralAccessPwd = centralAccessPwd == null ? null : centralAccessPwd.trim();
    }

	public List<Extension> getExtensionList() {
		return extensionList;
	}

	public void setExtensionList(List<Extension> extensionList) {
		this.extensionList = extensionList;
	}

	@Override
	public String toString() {
		return "CentralMaster [idCentralNumber=" + idCentralNumber + ", centralNumber=" + centralNumber
				+ ", centralCode=" + centralCode + ", centralNumberPstn=" + centralNumberPstn + ", centralSmgrIp="
				+ centralSmgrIp + ", centralLang=" + centralLang + ", centralAccessPwd=" + centralAccessPwd
				+ ", extensionList=" + extensionList + "]";
	}
	
	
    
}