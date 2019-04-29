package com.parlana.core.rest.response.dto;

public class ExtensionResponseDTO {

	private Long idExtension;

    private Long idCentralNumber;

    private String extensionNumber;

    private String extensionNumberPstn;

    private String extensionAreaName;

    private String extensionIntentCode;

    private String extensionIntentConfidence;

    private String extensionPersonFullname;

    private String extensionPersonEmail;

    private String extensionPersonPhone;

    public Long getIdExtension() {
        return idExtension;
    }

    public void setIdExtension(Long idExtension) {
        this.idExtension = idExtension;
    }

    public Long getIdCentralNumber() {
        return idCentralNumber;
    }

    public void setIdCentralNumber(Long idCentralNumber) {
        this.idCentralNumber = idCentralNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber == null ? null : extensionNumber.trim();
    }

    public String getExtensionNumberPstn() {
        return extensionNumberPstn;
    }

    public void setExtensionNumberPstn(String extensionNumberPstn) {
        this.extensionNumberPstn = extensionNumberPstn == null ? null : extensionNumberPstn.trim();
    }

    public String getExtensionAreaName() {
        return extensionAreaName;
    }

    public void setExtensionAreaName(String extensionAreaName) {
        this.extensionAreaName = extensionAreaName == null ? null : extensionAreaName.trim();
    }

    public String getExtensionIntentCode() {
        return extensionIntentCode;
    }

    public void setExtensionIntentCode(String extensionIntentCode) {
        this.extensionIntentCode = extensionIntentCode == null ? null : extensionIntentCode.trim();
    }

    public String getExtensionIntentConfidence() {
        return extensionIntentConfidence;
    }

    public void setExtensionIntentConfidence(String extensionIntentConfidence) {
        this.extensionIntentConfidence = extensionIntentConfidence == null ? null : extensionIntentConfidence.trim();
    }

    public String getExtensionPersonFullname() {
        return extensionPersonFullname;
    }

    public void setExtensionPersonFullname(String extensionPersonFullname) {
        this.extensionPersonFullname = extensionPersonFullname == null ? null : extensionPersonFullname.trim();
    }

    public String getExtensionPersonEmail() {
        return extensionPersonEmail;
    }

    public void setExtensionPersonEmail(String extensionPersonEmail) {
        this.extensionPersonEmail = extensionPersonEmail == null ? null : extensionPersonEmail.trim();
    }

    public String getExtensionPersonPhone() {
        return extensionPersonPhone;
    }

    public void setExtensionPersonPhone(String extensionPersonPhone) {
        this.extensionPersonPhone = extensionPersonPhone == null ? null : extensionPersonPhone.trim();
    }
    
}
