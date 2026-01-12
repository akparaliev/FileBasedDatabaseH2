import Entities.AcademyGroup;
import Repository.AcademyGroupRepository;

public class App {
    public static void main(String[] args) throws Exception {
        AcademyGroupRepository groupRepository = new AcademyGroupRepository("group.file");
        groupRepository.Add(new AcademyGroup(0, "Group 2025"));
        groupRepository.Add(new AcademyGroup(1, "Group 2026"));

        for(AcademyGroup group: groupRepository.GetAll()){
            System.out.println(group.getId() + ", " + group.getName());
        }
    }
}