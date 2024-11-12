package Service;

import Domain.Membership;
import Repository.IRepository;
import java.util.List;

public class MembershipService {
    private IRepository<Membership> membershipRepository;

    public MembershipService(IRepository<Membership> membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public boolean addMembership(Membership membership) {
        return membershipRepository.create(membership);
    }

    public boolean updateMembership(Membership membership) {
        return membershipRepository.update(membership);
    }

    public boolean deleteMembership(int membershipID) {
        return membershipRepository.delete(membershipID);
    }

    public Membership getMembership(int membershipID) {
        return membershipRepository.read(membershipID);
    }

    public List<Membership> getAllMemberships() {
        return membershipRepository.readAll();
    }
}
