export interface SecurityPlugin {
  isDeveloperModeEnabled(): Promise<{ enabled: boolean }>;
  isRooted(): Promise<{ rooted: boolean }>;
}
