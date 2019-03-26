package dto;

import java.util.UUID;

public class EditMemberDTO {
    private boolean addMember;
    private int projectId;
    private UUID memberId;

    public EditMemberDTO(){

    }

    public boolean isAddMember() {
        return addMember;
    }

    public void setAddMember(boolean addMember) {
        this.addMember = addMember;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }
}
