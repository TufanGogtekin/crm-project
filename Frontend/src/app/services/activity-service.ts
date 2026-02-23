import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivityInterface } from '../interfaces/activity-interface';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {

  private activityPath = "http://localhost:8080/rest/api/activity";

  constructor(private http: HttpClient) { }

  saveActivity(data: ActivityInterface) {
    return this.http.post<ActivityInterface>(`${this.activityPath}/save`, data);
  }

  getAllActivities() {
    return this.http.get<ActivityInterface[]>(`${this.activityPath}/list`);
  }
  
}
