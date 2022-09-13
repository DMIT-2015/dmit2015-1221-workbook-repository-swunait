package dmit2015.javabean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is use to calculate a person's body mass index (BMI) and their BMI Category.
 *
 * @author Sam Wu
 * @version 2022.09.12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyMassIndex {

    private String name;
    private double weight;
    private double height;

    /**
     * Calculate the body mass index (BMI) using the weight and height of the person.
     * The BMI of a person is calculated using the formula: BMI = 700 * weight / (height * height)
     * where weight is in pounds and height is in inches.
     * @return the body mass index (BMI) value of the person
     */
    public double bmi() {
        double bmiValue = 703 * weight / (height * height);
        bmiValue = Math.round(bmiValue * 10) / 10.0;
        return bmiValue;
    }

    /**
     * Determines the BMI Category of the person using their BMI value and
     * comparing it against the following table.
     * <table>
     * <thead>
     * <tr>
     * <th>BMI Range</th>
     * <th>BMI Category</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>< 18.5</td>
     * <td>underweight</td>
     * </tr>
     * <tr>
     * <td>>= 18.5 and < 25</td>
     * <td>normal</td>
     * </tr>
     * <tr>
     * <td>>= 25 and < 30</td>
     * <td>overweight</td>
     3.
     http://localhost:8080/javawockee-web/sections/dmit2015/winter2016/exe...
     of 4 1/13/2016 5:21 PM
     * </tr>
     * <tr>
     * <td>>= 30</td>
     * <td>obese</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return one of following: underweight, normal, overweight, obese.
     */
    public String bmiCategory() {
        String category = "Unknown";
        double bmiValue = bmi();

        if (bmiValue < 18.5) {
            category = "underweight";
        } else if (bmiValue < 24.9) {
            category = "normal";
        } else if (bmiValue < 29.9) {
            category = "overweight";
        } else {
            category = "obese";
        }

        return category;
    }

}
