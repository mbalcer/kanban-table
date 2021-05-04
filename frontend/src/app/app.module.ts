import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {TasksComponent} from './tasks/tasks.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {AuthService} from './auth-service/auth.service';
import {CryptoJsService} from './crypto-js-service/crypto-js.service';
import {StudentService} from './student/student.service';
import {AuthGuardService} from './auth-guard/auth-guard.service';
import {AppRoutingModule} from './app-routing.module';
import {DialogTaskDetails} from './tasks/dialog-task-details/dialog-task-details';
import {MaterialModule} from './material-module';
import {InitialsPipe} from './tasks/pipes/initials.pipe';
import {StudentNamePipe} from './tasks/pipes/student-name.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    LoginComponent,
    RegistrationComponent,
    DialogTaskDetails,
    InitialsPipe,
    StudentNamePipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    DragDropModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [
    AuthService,
    AuthGuardService,
    CryptoJsService,
    StudentService
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogTaskDetails]
})
export class AppModule {
}
