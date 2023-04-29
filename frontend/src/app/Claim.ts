export interface Claim{

    claim_Id: string
    policy_No: string
    estimated_Loss: number
    date_Of_Accident: Date
    claim_Status: string
    surveyor_Id: number
    amount_ApprovedBy_Surveyor: number
    insurance_Company_Approval: boolean
    with_Draw_Claim: boolean
    surveyor_Fees: number

}
