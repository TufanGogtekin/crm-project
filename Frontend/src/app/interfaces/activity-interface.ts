export interface ActivityInterface {
    id?: number;
    subject: string;     
    type: string;         
    date: string;          
    hour: string;          
    duration: number;     
    description: string; 
    status:string;  
    
    customerId: number;    
    employeeId: number;   
}
