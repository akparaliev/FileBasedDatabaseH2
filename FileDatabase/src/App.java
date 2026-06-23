import Entities.Academy;
import Entities.Group;
import Entities.Mentor;
import Repository.AcademyRepository;
import Repository.DbContext;
import Repository.GroupRepository;
import Repository.IRepository;
import Repository.MentorRepository;
import Repository.RepositoryWALDecorator;

public class App {
    public static void main(String[] args) throws Exception {
        String dbFilePath = "db.file";
        String walFileName = "wal.log";
        DbContext context = new DbContext(dbFilePath);
        IRepository<Academy> academyRepository = new RepositoryWALDecorator(new AcademyRepository(context), walFileName);
        IRepository<Group> groupRepository = new RepositoryWALDecorator(new GroupRepository(context), walFileName);
        IRepository<Mentor> mentorRepository = new RepositoryWALDecorator(new MentorRepository(context), walFileName);

        academyRepository.Add(new Academy(0, "GrowthHungry"));

        groupRepository.Add(new Group(0, "March", 0));
        groupRepository.Add(new Group(1, "Group 2026", 0));
        mentorRepository.Add(new Mentor(0, "Shabdan", 0));

        // select name from Groups where academyId = 0
        for (var group : ((GroupRepository)groupRepository).GetByAcademyId(0)) {
            System.out.println(group.getName());
        } 
        
        DbBackup backup = new DbBackup(dbFilePath); // should be in background thread
        backup.RunEvery(0, 30); // every 30 seconds
    }
}