import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivityInterface } from '../interfaces/activity-interface';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  constructor(private http: HttpClient) {}

  private activitySavePath = "http://localhost:8080/rest/api/activity/save";
  private activityListPath = "http://localhost:8080/rest/api/activity/list";

  saveActivity(data: ActivityInterface) {
    return this.http.post<ActivityInterface>(this.activitySavePath, data);
  }

  getAllActivities(){
    return this.http.get<ActivityInterface[]>(this.activityListPath);
  }
}
