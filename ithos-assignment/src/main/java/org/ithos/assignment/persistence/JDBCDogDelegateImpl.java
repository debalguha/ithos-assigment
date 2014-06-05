package org.ithos.assignment.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.ithos.assignment.dto.Dog;
import org.ithos.assignment.dto.Animal;
import org.ithos.assignment.persistence.model.AnimalType;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JDBCDogDelegateImpl implements Delegate {

	private DataSource dataSource;
	
	public Animal findAnimalByCodeNum(int codeNum) {
		try {
			String sql = "select a.code_number, a.name, a.type, d.breed, d.paw_size, l.place from Animal a left outer join Dog d on d.code_number = a.code_number left outer join AnimalLocation al on al.animal_code = a.code_number left join Location l on l.id = al.location_id where a.code_number = ?";
			PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
			stmt.setInt(1, codeNum);
			ResultSet rs = stmt.executeQuery();
			return parseResultSet(rs).iterator().next();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<? extends Animal> parseResultSet(ResultSet rs) throws Exception{
		Dog dog = new Dog();
		int lastCodeNumber = -1;
		List<Dog> dogs = Lists.newArrayList();
		Set<String> locations = Sets.newHashSet();
		while(rs.next()){
			int codeNumber = rs.getInt(1);
			String name = rs.getString(2);
			String type = rs.getString(3);
			String breed = rs.getString(4);
			double pawSize = rs.getDouble(5);
			String location = rs.getString(6);
			if(lastCodeNumber !=-1 && lastCodeNumber != codeNumber){
				dog.setAnimalLocations(locations);
				dogs.add(dog);
				dog = new Dog();
				locations.clear();
			}else if(lastCodeNumber ==-1 || (lastCodeNumber != codeNumber)){
				dog.setCodeNumber(codeNumber);
				dog.setName(name);
				dog.setType(AnimalType.valueOf(type));
				dog.setBreed(breed);
				dog.setPawSize(pawSize);
			}
			locations.add(location);
			lastCodeNumber = codeNumber;
		}
		dog.setAnimalLocations(locations);
		dogs.add(dog);		
		return dogs;
	}

	@SuppressWarnings("unchecked")
	public List<Animal> findAnimalByName(String name) {
		try {
			String sql = "select a.code_number, a.name, a.type, d.breed, d.paw_size, l.place from Animal a left outer join Dog d on d.code_number = a.code_number left outer join AnimalLocation al on al.animal_code = a.code_number left join Location l on l.id = al.location_id where a.name like ?";
			PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
			stmt.setString(1, "%".concat(name).concat("%"));
			ResultSet rs = stmt.executeQuery();
			return (List<Animal>)parseResultSet(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
