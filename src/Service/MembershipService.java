package Service;

import Domain.Membership;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class MembershipService {
    private IRepository<Membership> membershipRepository;

    public MembershipService(IRepository<Membership> membershipRepository) {
        this.membershipRepository = membershipRepository;
        /**
         * Initializes a new instance of MembershipService with a FileRepository and DBRepository.
         */
//        this.membershipRepository = new FileRepository<>(
//                "memberships.txt",
//                Membership::getMembershipID,
//                line -> {
//                    String[] parts = line.split(",");
//                    return new Membership(
//                            Integer.parseInt(parts[0]),         //membershipID
//                            new Date(Long.parseLong(parts[1])), //startDate (as timestamp)
//                            new Date(Long.parseLong(parts[2])), //endDate (as timestamp)
//                            parts[3]                            //membershipType
//                    );
//                },
//                membership -> String.join(",",
//                        String.valueOf(membership.getMembershipID()),
//                        String.valueOf(membership.getStartDate().getTime()),
//                        String.valueOf(membership.getEndDate().getTime()),
//                        membership.getMembershipType()
//                )
//        );
    }

    /**
     * Adds a new membership to the repository.
     *
     * @param membership The membership to add.
     */
    public boolean addMembership(Membership membership) {
        return membershipRepository.create(membership);
    }

    /**
     * Updates a membership.
     *
     * @param membership The membership to update.
     */
    public boolean updateMembership(Membership membership) {
        return membershipRepository.update(membership);
    }

    /**
     * Deletes a membership.
     *
     * @param membershipID ID of the membership that is to be deleted.
     */
    public boolean deleteMembership(int membershipID) {
        return membershipRepository.delete(membershipID);
    }

    /**
     * Retrieves a membership by ID.
     *
     * @param membershipID ID of the wanted membership.
     * @return The membership with the specified ID.
     */
    public Membership getMembership(int membershipID) {
        return membershipRepository.read(membershipID);
    }

    /**
     * Retrieves all memberships.
     *
     * @return List of all memberships.
     */
    public List<Membership> getAllMemberships() {
        return membershipRepository.readAll();
    }
}
