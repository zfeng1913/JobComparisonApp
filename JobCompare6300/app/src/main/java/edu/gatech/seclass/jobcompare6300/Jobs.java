package edu.gatech.seclass.jobcompare6300;

public class Jobs {
    private Boolean isCurrentJob;
    private String title;
    private String company;
    private String city;
    private String state;
    private int cost_of_living;
    private double yearly_salary;
    private double yearly_bonus;
    private double number_of_stock_option_shares_offered;
    private double home_buying_program_fund;
    private int personal_choice_holidays;
    private double monthly_internet_stipend;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    //public Location getLocation() { return Location; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCostOfLiving() {
        return cost_of_living;
    }

    public void setCostOfLiving(int cost_of_living) {
        this.cost_of_living = cost_of_living;
    }

    public double getYearlySalary() {
        return yearly_salary;
    }

    public void setYearlySalary(double yearly_salary) {
        this.yearly_salary = yearly_salary;
    }

    public double getYearlyBonus() {
        return yearly_bonus;
    }

    public void setYearlyBonus(double yearly_bonus) {
        this.yearly_bonus = yearly_bonus;
    }

    public double getNumberOfStockOptionsSharesOffered() {
        return number_of_stock_option_shares_offered;
    }

    public void setNumberOfStockOptionsSharesOffered(double number_of_stock_option_shares_offered) {
        this.number_of_stock_option_shares_offered = number_of_stock_option_shares_offered;
    }
    public double getHomeBuyingProgramFund() {
        return home_buying_program_fund;
    }

    public void setHomeBuyingProgramFund(double home_buying_program_fund) {
        this.home_buying_program_fund = home_buying_program_fund;
    }

    public int getPersonalChoiceHolidays() {
        return personal_choice_holidays;
    }

    public void setPersonalChoiceHolidays(int personal_choice_holidays) {
        this.personal_choice_holidays = personal_choice_holidays;
    }
    public double getMonthlyInternetStipend() {
        return monthly_internet_stipend;
    }

    public void setMonthlyInternetStipend(double monthly_internet_stipend) {
        this.monthly_internet_stipend = monthly_internet_stipend;
    }

    /* DEPRICATED MOVED TO EnterNewJobOffers.java
    TODO: replicate into EnterCurrentJobDetails.java
    private boolean validateAttributes(){
        boolean validJob = false;
        if(this.home_buying_program_fund < (0.15 * this.yearly_salary) ||
                this.monthly_internet_stipend < 0.0 || this.monthly_internet_stipend > 75.0 ||
                this.personal_choice_holidays < 0 || this.personal_choice_holidays > 20)
        {validJob = true;} // TODO: Just block the user from entering values like this?
        return validJob;
    }
    */


    public Boolean isTheCurrentJob(){ return isCurrentJob; }
    public void setCurrentJob(boolean current_job){ this.isCurrentJob = current_job; }

}
