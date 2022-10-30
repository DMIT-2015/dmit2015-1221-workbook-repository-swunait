package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Employees", schema = "WestWind")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EmployeeID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "LastName", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "FirstName", nullable = false, length = 10)
    private String firstName;
    @Basic
    @Column(name = "TitleOfCourtesy", nullable = true, length = 25)
    private String titleOfCourtesy;
    @Basic
    @Column(name = "JobTitle", nullable = true, length = 30)
    private String jobTitle;
    @Basic
    @Column(name = "ReportsTo", nullable = true)
    private Integer reportsTo;
    @Basic
    @Column(name = "HireDate", nullable = false)
    private LocalDate hireDate;
    @Basic
    @Column(name = "OfficePhone", nullable = true, length = 24)
    private String officePhone;
    @Basic
    @Column(name = "Extension", nullable = true, length = 4)
    private String extension;
    @Basic
    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;
    @Basic
    @Column(name = "AddressID", nullable = false)
    private Integer addressId;
    @Basic
    @Column(name = "HomePhone", nullable = false, length = 24)
    private String homePhone;
    @Basic
    @Column(name = "Photo", nullable = true)
    private byte[] photo;
    @Basic
    @Column(name = "PhotoMimeType", nullable = true, length = 40)
    private String photoMimeType;
    @Basic
    @Column(name = "Notes", nullable = true, length = -1)
    private String notes;
    @Basic
    @Column(name = "Active", nullable = true)
    private Boolean active;
    @Basic
    @Column(name = "TerminationDate", nullable = true)
    private LocalDate terminationDate;
    @Basic
    @Column(name = "OnLeave", nullable = false)
    private Boolean onLeave;
    @Basic
    @Column(name = "LeaveReason", nullable = true, length = 80)
    private String leaveReason;
    @Basic
    @Column(name = "ReturnDate", nullable = true)
    private LocalDate returnDate;
    @OneToMany(mappedBy = "employeesByEmployeeId")
    private Collection<EmployeeTerritory> employeeTerritoriesByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "ReportsTo", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    private Employee employeesByReportsTo;
    @OneToMany(mappedBy = "employeesByReportsTo")
    private Collection<Employee> employeesByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "AddressID", referencedColumnName = "AddressID", nullable = false, insertable = false, updatable = false)
    private Address addressesByAddressId;
    @OneToMany(mappedBy = "employeesBySalesRepId")
    private Collection<Order> ordersByEmployeeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoMimeType() {
        return photoMimeType;
    }

    public void setPhotoMimeType(String photoMimeType) {
        this.photoMimeType = photoMimeType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Boolean getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(Boolean onLeave) {
        this.onLeave = onLeave;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(titleOfCourtesy, employee.titleOfCourtesy) && Objects.equals(jobTitle, employee.jobTitle) && Objects.equals(reportsTo, employee.reportsTo) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(officePhone, employee.officePhone) && Objects.equals(extension, employee.extension) && Objects.equals(birthDate, employee.birthDate) && Objects.equals(addressId, employee.addressId) && Objects.equals(homePhone, employee.homePhone) && Arrays.equals(photo, employee.photo) && Objects.equals(photoMimeType, employee.photoMimeType) && Objects.equals(notes, employee.notes) && Objects.equals(active, employee.active) && Objects.equals(terminationDate, employee.terminationDate) && Objects.equals(onLeave, employee.onLeave) && Objects.equals(leaveReason, employee.leaveReason) && Objects.equals(returnDate, employee.returnDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, lastName, firstName, titleOfCourtesy, jobTitle, reportsTo, hireDate, officePhone, extension, birthDate, addressId, homePhone, photoMimeType, notes, active, terminationDate, onLeave, leaveReason, returnDate);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    public Collection<EmployeeTerritory> getEmployeeTerritoriesByEmployeeId() {
        return employeeTerritoriesByEmployeeId;
    }

    public void setEmployeeTerritoriesByEmployeeId(Collection<EmployeeTerritory> employeeTerritoriesByEmployeeId) {
        this.employeeTerritoriesByEmployeeId = employeeTerritoriesByEmployeeId;
    }

    public Employee getEmployeesByReportsTo() {
        return employeesByReportsTo;
    }

    public void setEmployeesByReportsTo(Employee employeesByReportsTo) {
        this.employeesByReportsTo = employeesByReportsTo;
    }

    public Collection<Employee> getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Collection<Employee> employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    public Address getAddressesByAddressId() {
        return addressesByAddressId;
    }

    public void setAddressesByAddressId(Address addressesByAddressId) {
        this.addressesByAddressId = addressesByAddressId;
    }

    public Collection<Order> getOrdersByEmployeeId() {
        return ordersByEmployeeId;
    }

    public void setOrdersByEmployeeId(Collection<Order> ordersByEmployeeId) {
        this.ordersByEmployeeId = ordersByEmployeeId;
    }
}
