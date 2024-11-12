package Controller;

import Domain.Membership;
import Service.MembershipService;

import java.util.Date;
import java.util.List;

public class MembershipController {
    private MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    public boolean addMembership(int membershipID, Date startDate, Date endDate, String membershipType) {
        if (membershipType == null || membershipType.isEmpty()) {
            System.out.println("Membership type cannot be empty.");
            return false;
        }

        Membership membership = new Membership(membershipID, startDate, endDate, membershipType);
        return membershipService.addMembership(membership);
    }

    public boolean updateMembership(int membershipID, Date startDate, Date endDate, String membershipType) {
        if (membershipType == null || membershipType.isEmpty()) {
            System.out.println("Membership type cannot be empty.");
            return false;
        }

        Membership membership = new Membership(membershipID, startDate, endDate, membershipType);
        return membershipService.updateMembership(membership);
    }

    public boolean deleteMembership(int membershipID) {
        return membershipService.deleteMembership(membershipID);
    }

    public Membership getMembership(int membershipID) {
        return membershipService.getMembership(membershipID);
    }

    public List<Membership> getAllMemberships() {
        return membershipService.getAllMemberships();
    }
}
