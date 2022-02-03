package id.go.kebumenkab.simpus.model.patient.dummy

object PatientsData {
    private val names = arrayOf("Kurniawan Saputra", "Dwi Irawan", "Bagas Sasongko")
    private val idNumbers = arrayOf("33060912343567891", "3308091234565786", "3309080728937812")
    private val medicalRecords = arrayOf("No. RM: 2022-330512-777-8", "No. RM: 2022-330512-777-9", "No. RM: 2022-330512-777-8")

    val patientList: ArrayList<Patient>
        get() {
            val list = arrayListOf<Patient>()
            for (position in names.indices) {
                val patient = Patient()
                patient.name = names[position]
                patient.idNumber = idNumbers[position]
                patient.medicalRecord = medicalRecords[position]
                list.add(patient)
            }
            return list
        }
}