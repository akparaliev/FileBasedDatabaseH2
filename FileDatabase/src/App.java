import Entities.Academy;
import Entities.Group;
import Entities.Mentor;
import Repository.AcademyRepository;
import Repository.DbContext;
import Repository.GroupRepository;
import Repository.IRepository;
import Repository.MentorRepository;

public class App {
    public static void main(String[] args) throws Exception {
        DbContext context = new DbContext("db.file");
        IRepository<Academy> academyRepository = new AcademyRepository(context);
        IRepository<Group> groupRepository = new GroupRepository(context);
        IRepository<Mentor> mentorRepository = new MentorRepository(context);

        academyRepository.Add(new Academy(0, "GrowthHungry"));
        groupRepository.Add(new Group(0, "March", 0));
        groupRepository.Add(new Group(1, "Group 2026", 0));
        mentorRepository.Add(new Mentor(0, "Shabdan", 0));

        // select name from Groups where academyId = 0
        for (var group : ((GroupRepository)groupRepository).GetByAcademyId(0)) {
            System.out.println(group.getName());
        }        
    }
}