package Service;

import Domain.Membership;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class MembershipService {
    private IRepository<Membership> membershipRepository;

    public MembershipService(IRepository<Membership> membershipRepository) {
        //this.membershipRepository = membershipRepository;
        this.membershipRepository = new FileRepository<>(
                "memberships.txt",
                Membership::getMembershipID,
                line -> {
                    String[] parts = line.split(",");
                    return new Membership(
                            Integer.parseInt(parts[0]),         //membershipID
                            new Date(Long.parseLong(parts[1])), //startDate (as timestamp)
                            new Date(Long.parseLong(parts[2])), //endDate (as timestamp)
                            parts[3]                            //membershipType
                    );
                },
                membership -> String.join(",",
                        String.valueOf(membership.getMembershipID()),
                        String.valueOf(membership.getStartDate().getTime()),
                        String.valueOf(membership.getEndDate().getTime()),
                        membership.getMembershipType()
                )
        );
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
