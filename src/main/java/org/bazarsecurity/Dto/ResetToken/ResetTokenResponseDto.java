package org.bazarsecurity.Dto.ResetToken;
// DTO para representar la respuesta de un token de reseteo
public class ResetTokenResponseDto {

    private String resetTokenId;
    private String resetToken;

    public ResetTokenResponseDto() {
    }

    public ResetTokenResponseDto(String resetTokenId, String resetToken) {
        this.resetTokenId = resetTokenId;
        this.resetToken = resetToken;
    }

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
